pipeline {
    agent any

    stages {
        stage('build') {
            steps {
                echo 'Building...'
            }
        }

        stage('maven install') {
            steps {
                withMaven {
                    sh 'mvn clean package install'
                }
            }
        }

        stage('deploy') {
            steps {
                echo 'Deploying...'
            }
        }
    }

}