1. Project description
The project  based on REST architecture, is an imitation of brokerage office  -  user can create account, create wallet, buy and sell chosen shares. 
Application allows users to check real prices of shares from NYSE US using external APIs . 
At this moment both backend-(in Java) and frontend-(in Vaadin) parts are not separeted.

2. Requirements
Project is not uploaded to remote server yet, so user  needs   following:
     1.Java 8
     2. Vaadin version 14.4.8
     3. MySQL
     4.Gradle

3. How to run Project
     1. Create Database (MySQL) according to application.properties file
     2. Run stock_trading_platform\src\main\java\com\kodilla\stock_trading_platform\StockTradingPlatformApplication.java
     3.Enter  http://localhost:8080/

4. Application development plan
     1. Adding tests for all controllers, mappers and services.
     2. Adding Scheduler – daily emails raporting the value of the wallet 
     3. Deploying on Heroku 

5. Troubleshooting
If You encounter any problems regarding operation, please let me know.
