pipeline {
    agent any

    options {
        ansiColor('xterm')
    }

    stages {

      

        stage('Build') {
            steps {
                echo 'Buildeando...'
                withGradle {
    // some block
    sh '''
                 ./gradlew assemble \
       '''
                            }


            }

        post{
            success{
                echo 'Archivando...'
                archiveArtifacts artifacts: 'build/libs/*.jar'
            }
             }
        }
        stage('Deploying') {
            steps {

                echo 'Se arcivó el artefacto, Desplegando...'
                sh 'docker-compose up -d'
                //'java -jar  build/libs/hello-srping-0.0.1-SNAPSHOT.jar'
            }
        }

          stage('gitlab'){
            steps{
                echo 'Notify Gitlab'
                updateGitlabCommitStatus name: 'build' , state: 'pending'
                updateGitlabCommitStatus name: 'build' , state: 'success'
            }
        }

        }
}
