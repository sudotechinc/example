README
======

This program allows a user to add and update employees. Each action on the employee is tracked in the audit_history table.
This demonstrates the linking of database tables. In this case, the audit_history table is linked to the employees table and users table.

To Run the Program

1. Run the MySQL DB script: sql/table-setup.sql
   - This script has two new tables: audit_history and users
   
2. Open the Project in Eclipse
   - Run the main program: EmployeeSearchApp.java

3. Login to the application 
   a. At the login screen, select any employee from the drop-down list
   b. The password field is not used, you can skip it
   c. Press OK to login

   Note: at this point, all of your employee actions will be associated with the user that you just logged in as.
   
4. Add a new employee
   - Click the "Add Employee" button
   - Fill out the employee info and save the employee
   
   Note: the employee is saved and a new record is added to the audit_history table
   
5. View the Audit History
   - Select the same employee from the list
   - Click the "View History" button
   - You will see the actions performed on this employee
   
You can perform similar actions for update employee.
          
----

The audit history is stored in the table:  audit_history

You can use the following SQL to view the entries in the table:

SELECT history.user_id, history.employee_id, history.action, history.action_date_time, users.first_name, users.last_name 
FROM demo.audit_history history, demo.users users
where history.user_id=users.id;
  
   
   
   