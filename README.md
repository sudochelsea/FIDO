                        # Maven dependencies and build file

# FIDO
#Project Structure

# To run this app, you will need to follow these 3 steps:

# Requirements

1.  Laptop

2. Text Editor or IDE (eg. vscode, intellij IDEA)

3. Git installed on your Laptop.


#  Local Setup and Running on Windows, Linux and Mac OS


1. Clone the Repository into the directory of your choice

   `$ git clone https://github.com/sudochelsea/FIDO.git`

2.  Move into project folder
    `$ cd Fido-test`

3.  Install Dependencies
    `$ mvn clean install`

# Running Tests

1. Execute All Tests 
 `  $ mvn test`

2. Execute Specific Test Classes
   `$ mvn -Dtest=AuthenticationTests test`

# Viewing Reports

1. Run Tests
  ` $ mvn test`

2. Serve the Allure Report
  `  $ allure serve allure-results`

# Continuous Integration (CI) With Jenkins 


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
                          branches: [[name: '*/main']],
                          userRemoteConfigs: [[
                              url: 'https://github.com/sudochelsea/FIDO.git', // Replace with your Git repo URL

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






