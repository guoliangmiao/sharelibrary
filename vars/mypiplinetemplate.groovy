#!groovy

def call(Map map) {
def obj = new org.Sharelib.otherTools()
pipeline {
  agent {
    kubernetes {
       cloud 'kubernetes'
       inheritFrom 'jenkins-agent'
    }
  }
  environment {
            SERVICE_NAME = "${map.SERVICE_NAME}" // 需要修改此处，定义部署到远程的项目名称
            GIT_URL = "${map.GIT_URL}"// 主项目地址
            BUILD_COMMAND="${map.BUILD_COMMAND}" // 定义项目编译命令
            //GITLAB_AUTH_TOKEN="auth-gitlab" // 与gitlab认证的token，不需要更改
            //GIT_TOKEN = "${map.GIT_TOKEN}"
        }
  stages {
    stage("init"){
      steps{  
        echo "初始化"
        echo SERVICE_NAME    
        }
    }
    stage("checkout"){
      steps{
        echo "拉取代码"
        //echo GIT_URL
        //echo GIT_TOKEN
      }
    }
    stage("build and package"){
      steps{
        echo "编译打包"
        echo BUILD_COMMAND

      }
    }
    stage("push"){
      steps{
        script {obj.PrintMes()}
        echo "推送" 
      }
    }
  }
}
}