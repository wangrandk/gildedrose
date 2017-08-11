node {
   stage('Preparation') {
        checkout scm
        stash excludes: 'target/', name: 'source'
   }
   
   stage('parallel'){
        parallel(
            'Build' : {
            	unstash 'source'
                sh "docker run --rm -i -v $WORKSPACE:/usr/src/mymaven -w /usr/src/mymaven maven:3-jdk-8 mvn clean package"  
       			junit '**/target/surefire-reports/TEST-*.xml'
       			stash includes: '**/target/*.jar', name: 'jar'
       },
       'Javadoc' : {
           	unstash 'source'
            sh "docker run --rm -i -v $WORKSPACE:/usr/src/mymaven -w /usr/src/mymaven maven:3-jdk-8 mvn site"
            stash includes: 'target/site/*', name: 'javadoc' 
       }
    )
   }
       
   stage('Results') {
        archiveArtifacts artifacts:'target/*.jar'
        archiveArtifacts artifacts:'target/site/'
   }
}

