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
                //'java -jar  build/libs/hello-srping-0.0.1-SNAPSHOT.jar' --> aqui tira directamente del .jar

    // Parte de ssh Agent
    //
                node {
                sshagent (credentials: ['sshJenkins]) {        
                sh 'git tag MAIN-1.0.${BUID_NUMBER}'
                sh 'git push --tags' 
  }
}
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
