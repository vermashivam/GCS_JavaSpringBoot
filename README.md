# GCS_JavaSpringBoot
Spring Boot application uploading  to Google Cloud Storage using Google Cloud Client API and viewing the object given object name and their bucket using publically accessible URL

Get Request : localhost:8080/storeFile
Through this an html form opens up where we can upload multiple image files which will be , on submit , will be uploaded to target bucket in Google Cloud Storage ( assuming necessary permission have been give to your service account).


Get Request : localhost:8080/listofimages
Through this we can pass on the list of objects names that we want to view from preferred bucket to the html form,if  objects with these name are present then <object_name , object_url_in_cloud_storage> pair will be added to Model object, and tha can be directly used in our template.

Post Request : localhost:8080/listofimages
Similar to Get request, but we can by pass the form , and can directly give the list of object name
