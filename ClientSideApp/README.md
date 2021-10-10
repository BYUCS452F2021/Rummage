![image](https://user-images.githubusercontent.com/13889317/136713098-d71d69a4-d553-473f-b0f7-fe1d0d2684e5.png)

The original code here is for an app called Tweeter (a Twitter clone). Below I will explain what some of the parts do:

* edu.byu.cs.tweeter.client:
    * model:
     * net: Each of these files help make the actual HTTP requests to the server. The server facade is used as an interface over this logic
      * service: These services provide abstraction to the server facade functions. They are principally created in and used by the presenters. They can also call other8 services.
      * presenter: Presenters check authorization to perform functions and call various services. They are typically created by and used in Tasks and Activities.
      * utils: Some helpful functions and classes that format data
      * view:
          * asyncTasks: an async task is a task that requires a call to the backend. These are abstracted as tasks that have both presenters (the task requesters) and Observers (some other interested class that will need to know when this task finishes in order to update what the user is seeing.)
          * login: Activities and fragments that control the login flow
          * main: houses many of the main adapters and activities that control general app flow
          * util: more helpful functions
   
* res:
      * layout: these files are configuration files that control the UI elements.
      * values: this houses various variable files that allow for a single location for all colors, test, and styles to be stored. These can be referenced in the layout files or the activities themselves.


(My guess is that we will choose to add another package to edu.byu.cs.tweeter.client that houses our services and daos since we are only querying local DBs instead of having this separate server module.)
* edu.byu.cs.tweeter.server:
      * dao: This is where actual queries on databases are performed.
      * lambda: for this app, the server was basically hosted on multiple lambdas, this won't be a part of our project
      * service: these files connect the lambdas with the daos and perform any business logic necesarry.


* edu.byu.cs.tweeter.shared:
      * domain: All of the files in the shared module have the base classes for our objects.
      * net: Server exception classes
      * service: holds request/response classes and service interfaces
          * request: Server Request classes
          * response: Server Response classes

