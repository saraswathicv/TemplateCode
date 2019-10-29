package com.devlite.gitpipeline;
import com.devlite.gitpipeline.*;
import groovy.util.XmlParser;
import groovy.util.XmlSlurper;
class NotifyMail implements Serializable { 

  def steps;
  def params;
  def currentBuild;
  NotifyMail(steps,params) {
      this.steps = steps
      this.params = params
      this.currentBuild=currentBuild
  }
  def sendMail(dev_instance_ENDURL,BuildStatus){
	  steps.echo params.Gitcodeurl 
  def splitgiturl=params.Gitcodeurl.split("/")
	  steps.echo splitgiturl.toString();
  def repo=splitgiturl[4].split(".")
	  steps.echo repo.toString()
  def gitreponame=repo[1]
 steps.echo gitreponame
   steps.emailext subject: 'The HelloWorld App - Build Status: $BUILD_DISPLAY_NAME is ' + BuildStatus , 
   body: '''
    Hi All,
  
        The Current Build BUILD_DISPLAY_NAME is '''+ BuildStatus + '''
        Please Find  the attached Build Logs: BUILD_URL
        DEV URL = ''' +  dev_instance_ENDURL + '''
        Please find the input parameter values:
        --------------
	      instance_name   = '''+params.instance_name+'''
	      Gitcodeurl      = '''+params.Gitcodeurl+'''
	      GitBranch       = '''+params.GitBranch+'''
	      Language        = '''+params.Language+'''
	      Languageversion = '''+params.Languageversion+'''	
	      ServerType      = '''+params.ServerType+'''
	      FieldType	      = '''+params.FieldType+'''
	      DevInstances    = '''+params.DevInstances+'''
	      DTshirtsize     = '''+params.DTshirtsize+'''
	      TestInstances   = '''+params.TestInstances+'''
	      TTshirtsize     = '''+params.TTshirtsize+'''
	      ProdInstances   = '''+params.ProdInstances+'''	
	      MAILIDs	      = '''+params.MAILIDs+'''
  Thanks,
  Devops Team ''', replyTo: 'no-reply@wipro-poc.com',
                        from:'no-reply@wipro-poc.com',
                        to: params.MAILIDs,
                        attachLog:'true'
                        //attachmentsPattern:'*.pdf'
  } 
 }
