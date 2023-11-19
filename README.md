# Log-Ingestor
Developed a log ingestor system that can efficiently handle vast volumes of log data, and offer a simple interface for querying this data using full-text search or specific field filters.

# 1) ABOUT THE PROJECT
Developed a log ingestor system that can efficiently handle vast volumes of log data, and offer a simple interface for querying this data using full-text search or specific field filters.
The logs are ingested (in the log ingestor) over HTTP, on port 3000.

# Build With
The project is buid by using
 # Backend
    -> Java 
    -> Spring Boot
 # Front End
    -> Thymeleaf
    -> JavaScript
 # Data Base
    -> MySql Data Base
 # IDE
    -> IntelliJ Idea
 # Version Controller
    -> Git/GitHub

# 2) GETTING STARTED    
     Here their are certain prerequisites before cloning the project
     # PREREQUISITES:-
     -> Steps to create the data base(MySQl)
          1) execute the following queries in the order mentioned for creating data base and table [**PLEASE IGNORE '"' WHILE EXECUTING QUERY**]
                 -> "create database demo;" (for creating the data base of name demo)
                 -> "use demo;" (to use the data base demo)
                 ->  "CREATE TABLE logger (
                        id BIGINT NOT NULL AUTO_INCREMENT,
                        created_date BIGINT,
                        status BOOLEAN,
                        created_by BIGINT,
                        updated_date BIGINT,
                        updated_by BIGINT,
                        level VARCHAR(255),
                        message VARCHAR(255),
                        resource_id VARCHAR(255),
                        trace_id VARCHAR(255),
                        span_id VARCHAR(255),
                        commit VARCHAR(255),
                        parent_id VARCHAR(255),
                        time_stamp VARCHAR(255),
                        json_string VARCHAR(255),
                        PRIMARY KEY (id),
                        INDEX idx_level (level),
                        INDEX idx_resource_id (resource_id),
                        INDEX idx_trace_id (trace_id),
                        INDEX idx_span_id (span_id),
                        INDEX idx_commit (commit),
                        INDEX idx_parent_id (parent_id),
                        INDEX idx_jsonString (json_string),
                        INDEX idx_created_date (created_date),
                        INDEX idx_status (status)
                    );"

     # INSTALLATION:-
       STEP 1) Clone the project
               -> git clone https://github.com/dyte-submissions/november-2023-hiring-saksham27gaur.git
       STEP 2) Enter the UserName & Password in Application.yml file
               -> UserName and Password should be your MYSql Workbench credentials
       STEP 3) Run the project
               -> Use any IDE, Intellij Idea preferred
       STEP 4) Insert the Logs by using the curl on postman:-[SIMPLY IMPORT THE CURL]
       
               curl --location 'localhost:3000/log/internal/api/v1/log-ingestion' \
                --header 'Content-Type: application/json' \
                --data '{
                    "level": "no_error",
                    "message": "Failed to connect to MongoDb",
                    "resourceId": "server-1234",
                    "timestamp": "2023-09-15T08:00:00Z",
                    "traceId": "abc-xyz-123",
                    "spanId": "span-456",
                    "commit": "5e5342f",
                    "metadata": {
                        "parentResourceId": "server-0987"
                    }
                }'

 # 3) USAGE
 STEP 1) After Running the project, Go  to Google Chrome and hit the api "http://localhost:3000/interface/search-filter" on the search bar. You will see the below image
 ![image](https://github.com/dyte-submissions/november-2023-hiring-saksham27gaur/assets/109803256/84343f1c-4ecd-4043-ae92-7beb7a407048)

 STEP 2) Enter the appropraite text to be searched in the text bar and hit **enter**, if that text is alligned in any of the logs entered , you would see the list of logs along with the created date and time.

 STEP 3) Filter option is also given where you can filter your query based on multiple fields such as
         -> level
         -> message
         -> resourceId
         -> timestamp
         -> traceId
         -> spanId
         -> commit
         -> metadata.parentResourceId
      -> combination of fileds can also be applied.
      -> logs between the created date can be also fetched  



# 4) FEATURES IMPLEMENTED
- Implement search within specific date ranges.
- Utilize regular expressions for search.
- Allow combining multiple filters.
- Provide real-time log ingestion and searching capabilities.
- Implement role-based access to the query interface.

# 5) IMAGES OF THE INTERFACE
- https://github.com/dyte-submissions/november-2023-hiring-saksham27gaur/assets/109803256/84343f1c-4ecd-4043-ae92-7beb7a407048
- https://github.com/dyte-submissions/november-2023-hiring-saksham27gaur/assets/109803256/20e21c9c-af85-42f6-8c8d-c810074185c7
- ![Uploading image.png…]()





    

