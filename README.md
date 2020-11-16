# myPLS README

============== PART 1. SETTING UP DATABASE ==============
1. Install MySQL Workbench in you local machine
2. Make a Database called "myplsDB"
3. Load the dummy database (src -> resources -> myplsDB.sql) to start with.


============== PART 2. RUNNING THE CODE ==============
1. Load the code into IntelliJ
2. Go to src -> resources -> hibernate.cfg.xml
	2.1. Edit property hibernate.connection.username with your local machine's mysql username
	2.2. Edit property hibernate.connection.password with your local machine's mysql password
	2.3. Edit property hibernate.connection.url with your local machine's mysql database created in Part 1. above
2. Right click on src -> main -> java -> com.mypls -> Application
	2.1. Click on Run Application.main()
3. The code is set to run on port 8080 by default
4. Navigate to your browser and type localhost:8080 in the browser
5. Begin with sign in or sign up.




============== PART 3. SOME DUMMY LOGINS TO TEST ==============
ADMIN
Email: farhanbuet09@gmail.com 
Password: 12345678

PROFESSOR
Email: shakil@gmail.com
Password: 12345678

LEARNER
Email: mahdi@gmail.com
Password: 12345678




============== APPENDIX: PROJECT STRUCTURE ==============
Note: (D) = Directory and (F) = File

|- src (D)
	|- main (D)
		|- java (D)
			|- com.mypls (D)
				|- appl (D)
				|- model (D)
				|- ui (D)
				|- util (D)
				|- Application.java (F)
		|- resources (D)
			|- public (D)
				|- css (D)
				|- img (D)
				|- js (D)
				|- lesson (D)
				|- profile (D)
				|- scss (D)
				|- vendor (D)
			|- spark.template.freemaker (D)
				|- course (D)
				|- footer (D)
				|- header (D)
				|- profile (D)
				|- quiz (D)
				|- user (D)