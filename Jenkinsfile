#!groovy

def GH_CREDS = '2c69d250-a91e-4941-a11b-b4c831b59b90'
//slackChannel = 'ds-nextgen-'
//slackTeamDomain = 'ibm-analytics'
//slackTokenCredentialId = '1d960160-45e6-48fe-a99c-66c1e25b4ced'
def envFileCr = '02ff88fe-0096-42ab-884c-710a70b60be9'
def afaasCredentialsId = '10a795c2-fc1a-4b35-a0e7-644dcfcacfb8'
def OSSRH = '9cff6b0c-d10e-42b0-818c-dac103c109c4'
def SIGNING = '5c0605ac-66ce-4dd7-97d9-ba9f6890ab68'
def SIGNING_KEYFILE = 'e6473c56-5b7a-4716-aaaf-c199c2ad8d5b'

properties([
   buildDiscarder(logRotator(artifactDaysToKeepStr: '5', artifactNumToKeepStr: '5', daysToKeepStr: '5', numToKeepStr: '5'))
])
pipeline {

  agent {
    label 'ds_worker'
  }

  options {
    skipDefaultCheckout()
  }

  stages {
    stage('Checkout') {
      steps {
        withCredentials([usernamePassword(credentialsId: GH_CREDS, passwordVariable: 'GH_CREDS_PSW', usernameVariable: 'GH_CREDS_USR')]) {
        script {
          defaultInit()
          applyCustomizations()
          checkoutResult = checkout scm
          //checkourResult = checkout([$class: 'GitSCM', branches: [[name: '*/main']], extensions: [], userRemoteConfigs: [[credentialsId: GH_CREDS, url: 'https://github.com/IBM/datastage-java-sdk.git']]])
          //commitHash = "${checkoutResult.GIT_COMMIT[0..6]}"
            sh '''
              #git config --global user.email $GH_SDKS_AUTOMATION_MAIL
              git config --global user.name ${GH_CREDS_USR}
              git config --global credential.username ${GH_CREDS_USR}
              git config --global credential.helper "!f() { echo password=${GH_CREDS_PSW}; echo; }; f"
              set +e
                pip3 install --upgrade bump2version
                bump2version -h
              set -e
            '''
          }
        }
      }
    }//checkout

    stage('QA') {
      steps {
              runTests()
        }
        post {
          always {
            junit (
              testResults: 'modules/datastage/target/surefire-reports/junitreports/*.xml'
            )
          }
        }
      }//QA

    stage('INTG') {
      steps {
        withCredentials([file(credentialsId: envFileCr, variable: 'ENV_CONFIG')]) {
            sh'''
              envFile="datastage_v3.env"
              set +e
                pwd
                #cd datastage-java-sdk
                rm -f ${envFile}
              set -e
              touch ${envFile}
              cat ${ENV_CONFIG} >> ${envFile}

              mvn test -DfailIfNoTests=false -Dtest=DatastageIT
            '''
        }
      }
      post {
        always {
          junit (
            testResults: 'modules/datastage/target/surefire-reports/junitreports/**DatastageIT.xml'
          )
        }
      }
    }//Intg

    stage('Publish[staging]') {
      environment {
        STAGE_ROOT = 'https://na.artifactory.swg-devops.com/artifactory/api/'
      }
      steps {
        bumpVersion(true)
        //publishStaging()
      }
    }//publishStaging

    stage('Publish[repository]') {
      when {
        beforeAgent true
        allOf {
          // Publish master branch, but not on the version update commit after just publishing
          branch 'main'
          not {
            changelog 'Update version *'
          }
        }
      }
      steps {
        withCredentials([usernamePassword(credentialsId: '9cff6b0c-d10e-42b0-818c-dac103c109c4', passwordVariable: 'OSSRH_PASSWORD', usernameVariable: 'OSSRH_USERNAME'),
                         usernamePassword(credentialsId: '5c0605ac-66ce-4dd7-97d9-ba9f6890ab68', passwordVariable: 'SIGNING_PSW', usernameVariable: 'SIGNING_USR'),
                         usernamePassword(credentialsId: 'e82221fd-260f-46db-b3aa-0cf8bca0de17', passwordVariable: 'GPG_PASSPHRASE', usernameVariable: 'GPG_KEYNAME'),
                         file(credentialsId: 'e6473c56-5b7a-4716-aaaf-c199c2ad8d5b', variable: 'SIGNING_KEYFILE')]) {
          // Throw away any temporary version changes used for stage/test
          sh 'git reset --hard'
          bumpVersion(false)
          // Push the version bump and release tag
          sh 'git push --tags origin HEAD:main'
          //publishPublic()
          sh'''
              export OSSRH_USERNAME=${OSSRH_USERNAME}
              export OSSRH_PASSWORD=${OSSRH_PASSWORD}
              export SIGNING_USR=${SIGNING_USR}
              export SIGNING_PSW=${SIGNING_PSW}
              export SIGNING_KEYFILE=${SIGNING_KEYFILE}
              export GPG_KEYNAME=${GPG_KEYNAME}
              export GPG_PASSPHRASE=${GPG_PASSPHRASE}
              mvn deploy --settings build/.travis.settings.xml -DskipTests
              mvn deploy -P central -DskipNexusStagingDeployMojo=false
          '''
          //publishDocs()
        }
      }
    }//publish repository
  }
}

def libName
def commitHash
def bumpVersion
def customizeVersion
def prefixSdkVersion

void defaultInit() {
  // Default to using bump2version
  bumpVersion = { isDevRelease ->
    newVersion = getNextVersion(isDevRelease)
    // Set an env var with the new version
    env.NEW_SDK_VERSION = newVersion
    doVersionBump(isDevRelease, newVersion)
  }

  doVersionBump = { isDevRelease, newVersion, allowDirty ->
    sh "/home/jenkins/.local/bin/bump2version --new-version ${newVersion} ${allowDirty ? '--allow-dirty': ''} ${isDevRelease ? '--no-commit' : '--tag --tag-message "Release {new_version}"'} patch"
  }

  getNextVersion = { isDevRelease ->
    // Identify what the next patch version is
    patchBumpedVersion = sh returnStdout: true, script: '/home/jenkins/.local/bin/bump2version --list --dry-run patch | grep new_version=.* | cut -f2 -d='
    // Now the customized new version
    return getNewVersion(isDevRelease, patchBumpedVersion)
  }

  // Default no-op implementation to use semverFormatVersion
  customizeVersion = { semverFormatVersion ->
    semverFormatVersion
  }
}

String getNewVersion(isDevRelease, version) {
  wipVersion = ''
  if (isDevRelease) {
    // Add uniqueness and build metadata to dev build versions
    wipVersion = "${version.trim()}-dev${currentBuild.startTimeInMillis}.${currentBuild.number}"
  } else {
    wipVersion = "${version.trim()}"
  }
  // Customize with lang specific requirements
  return customizeVersion(wipVersion)
}

// Language specific implementations of the methods:
// applyCustomizations()
// runTests()
// publishStaging()
// publishPublic()
// publishDocs()
// + other customizations
void applyCustomizations() {
  libName = 'java'
  originalDoVersionBump = doVersionBump
  // Customize doVersionBump with maven version updates
  doVersionBump = { isDevRelease, newVersion ->
    // Bump the poms first
    sh "mvn versions:set -DnewVersion=${newVersion} -DgenerateBackupPoms=false"
    sh 'git add -A'
    // Then bump everything else and commit (note we need to allow dirty because of the updated poms)
    originalDoVersionBump(isDevRelease, newVersion, true)
  }
}

void runTests() {
  sh 'mvn test'
}

void publishStaging() {
  withCredentials([usernamePassword(credentialsId: afaasCredentialsId, passwordVariable: 'ARTIFACTORY_APIKEY', usernameVariable: 'ARTIFACTORY_USER')]) {
    publishMaven('-P afaasCredentialsId')
  }
}

void publishPublic() {
  withCredentials([usernamePassword(credentialsId: '9cff6b0c-d10e-42b0-818c-dac103c109c4', passwordVariable: 'OSSRH_PASSWORD', usernameVariable: 'OSSRH_USERNAME')]) {
    publishMaven('-P central')
  }
}

void publishMaven(mvnArgs='') {
  withCredentials([usernamePassword(credentialsId: '5c0605ac-66ce-4dd7-97d9-ba9f6890ab68', passwordVariable: 'SIGNING_PSW', usernameVariable: 'SIGNING_USR'),
                   file(credentialsId: 'e6473c56-5b7a-4716-aaaf-c199c2ad8d5b', variable: 'SIGNING_KEYFILE')]) {
    sh "mvn deploy --settings build/.travis.settings.xml -DskipTests ${mvnArgs}"
  }
}

void publishDocs() {
  sh './scripts/javadoc/publish-doc.sh'
}
