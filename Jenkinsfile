pipeline {
    agent any

    environment {
        MAVEN_HOME = tool 'Maven'
        JAVA_HOME = tool 'JDK11'
    }

     triggers {
            pollSCM('H/5 * * * *') // Polls the repository every 5 minutes
        }

    stages {
        stage('Checkout Code') {
            steps {
                echo 'Checking out code from repository...'
                checkout([$class: 'GitSCM',
                          branches: [[name: '*/main']],  // Replace 'main' with your branch name if different
                          userRemoteConfigs: [[
                              url: 'https://github.com/your-username/your-repo.git', // Replace with your Git repo URL
                              credentialsId: 'your-credentials-id' // Replace with your Jenkins credentials ID
                          ]]])
            }
        }

        stage('Setup Environment') {
            steps {
                echo 'Setting up environment...'
                sh 'mvn clean'
            }
        }

        stage('Run Tests') {
            steps {
                echo 'Running automated tests...'
                sh 'mvn test'
            }

        }

        stage('Generate Reports') {
            steps {
                echo 'Generating Allure reports...'
                sh 'allure generate target/allure-results --clean -o allure-report'
            }
            post {
                always {
                    archiveArtifacts artifacts: 'allure-report/**', allowEmptyArchive: true
                }
            }
        }
    }

    post {
        always {
            echo 'Cleaning up workspace...'
            cleanWs()
        }
        success {
            echo 'Pipeline executed successfully!'
        }
        failure {
            echo 'Pipeline execution failed!'
        }
    }
}
