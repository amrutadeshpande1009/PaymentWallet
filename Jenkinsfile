pipeline {
    agent any

    tools {
        maven 'Maven'
    }

    environment {
        SONARQUBE_ENV = 'MySonarQubeServer' // Matches Jenkins config
    }

    stages {
        stage('Checkout') {
            steps {
                checkout scmGit(
                    branches: [[name: '*/main']],
                    extensions: [],
                    userRemoteConfigs: [[
                        url: 'https://github.com/amrutadeshpande1009/PaymentWallet.git',
                        credentialsId: 'aacbecd2-b608-4d02-9b31-35e3b16f9efe'
                    ]]
                )
            }
        }

        stage('Build') {
            steps {
                bat '''
                    cd paymentWallet
                    mvn clean install
                '''
            }
        }

        stage('SonarQube Analysis') {
            steps {
                withSonarQubeEnv(SONARQUBE_ENV) {
                    bat '''
                        cd paymentWallet
                        mvn sonar:sonar
                    '''
                }
            }
        }
    }

    post {
        success {
            echo '✅ Build and SonarQube analysis completed successfully!'
        }
        failure {
            echo '❌ Build failed.'
        }
    }
}
