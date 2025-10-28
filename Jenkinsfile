pipeline {
    agent any

    tools {
        maven 'Maven' // Matches your Jenkins config
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

        stage('Test') {
            steps {
                bat '''
                    cd paymentWallet
                    mvn test
                '''
            }
        }

        stage('Package') {
            steps {
                bat '''
                    cd paymentWallet
                    mvn package
                '''
            }
        }
    }

    post {
        success {
            echo '✅ Build completed successfully!'
        }
        failure {
            echo '❌ Build failed.'
        }
    }
}
