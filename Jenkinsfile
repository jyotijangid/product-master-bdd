pipeline{

    agent any

    stages {

        stage ('Compile Stage') {

            steps {
               
                    bat 'mvn compile'                

            }
        }
        
    	stage ('Test Stage') {

            steps {
                
                    bat 'mvn test'
                

            }
        }      
        
		
		stage ('Build Stage') {

            steps {
                
                    bat 'mvn install'
                

            }
        }   
        
        stage('Generate Cucumber report') {
            steps{
        			cucumber buildStatus: 'UNSTABLE',
                		reportTitle: 'My Cucumber Report',
                		fileIncludePattern: '**/*.json',
               			trendsLimit: 10,
                		classifications: [
                    		[
                        		'key': 'Browser',
                        		'value': 'Chrome'
                    		]
                		]
                  }
			}
        
        

    }

}
