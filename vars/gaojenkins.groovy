#!groovy
import org.Sharelib.test.*
def call(Map demo) {
pipeline {
  agent {
    kubernetes {
       cloud 'kubernetes'
       inheritFrom 'haimaxy-jnlp'
    }
  }
  environment {
            GIT_URL = "${demo.GIT_URL}"// 主项目地址
            BUILD_COMMAND="${demo.BUILD_COMMAND}" // 定义项目编译命令
            //GIT_TOKEN = "${demo.GIT_TOKEN}"
        }
  stages {
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
        script {PrintMes()}
        echo "推送"
      }
    }

  }
}
}
