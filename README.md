**App requirements:**  
Create a Spring Boot Java project with the console interface for University, which consists of departments and lectors.  
The lectors could work in more than one department. A lector could have one degree: assistant, associate professor or professor.  
All data is stored in the relational database. The app should implement such commands:  
  
1. **"Head of department"**  
<ins>User input</ins>:  
show head of {department_name}  
<ins>App output</ins>:  
Head of {department_name} department is {head_of_department_name}  
  
2. **"Department statistics"**  
<ins>User input</ins>:  
show statistics of {department_name}  
<ins>App output</ins>:  
assistants - {assistants_count}.  
associate professors - {associate_professors_count}  
professors - {professors_count}  
  
3. **"Average salary"**  
<ins>User input</ins>:  
show the average salary for {department_name}  
<ins>App output</ins>:  
The average salary of department {department_name} is {average_salary}  
  
4. **"Count of employee"**  
<ins>User input</ins>:  
show count of employees for {department_name}  
<ins>App output</ins>:  
The count of employees of department {department_name} is {employee_count}  
  
5. **"Global search"**  
<ins>User input</ins>:  
global search by {template}  
<ins>Example</ins>:  
Global search by **van**  
<ins>App output</ins>:  
I**van** Petrenko, Petro I**van**enko  
