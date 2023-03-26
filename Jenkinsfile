pipeline {
    agent any

    stages {
        stage('maven install') {
            steps {
                withMaven(maven: 'maven3') {
                    bat 'mvn clean package install'
                }
            }
        }
    }

}