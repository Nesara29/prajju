<div align="center">

<img src="https://capsule-render.vercel.app/api?type=waving&color=0:090C14,50:161d33,100:0d1120&height=220&section=header&text=JOB%20PORTAL&fontSize=54&fontColor=EDEAE0&animation=fadeIn&fontAlignY=38&desc=Spring%20Boot%20%C2%B7%20Spring%20Security%20%C2%B7%20WebSocket%20Chat%20%C2%B7%20MySQL&descAlignY=58&descSize=16&descColor=5EEAD4" width="100%"/>

<img src="https://readme-typing-svg.demolab.com?font=Space+Grotesk&size=20&duration=3000&pause=1000&color=C9A227&center=true&vCenter=true&width=650&lines=Employer+posts+a+job...;Jobseeker+applies...;They+chat+about+it+live." alt="Typing SVG"/>

<br/><br/>

<p>
  <img src="https://img.shields.io/badge/Language-Java-ED8B00?style=for-the-badge&logo=openjdk&logoColor=white" alt="Java"/>
  <img src="https://img.shields.io/badge/Framework-Spring%20Boot%203.1-6DB33F?style=for-the-badge&logo=springboot&logoColor=white" alt="Spring Boot 3.1"/>
  <img src="https://img.shields.io/badge/Database-MySQL-4479A1?style=for-the-badge&logo=mysql&logoColor=white" alt="MySQL"/>
  <img src="https://img.shields.io/badge/Realtime-WebSocket-010101?style=for-the-badge&logo=socketdotio&logoColor=white" alt="WebSocket"/>
  <img src="https://img.shields.io/badge/License-Educational%20Use-7F5AF0?style=for-the-badge" alt="License"/>
</p>

<p>
  <img src="https://img.shields.io/badge/Security-Spring%20Security-red?style=flat-square" alt="Spring Security"/>
  <img src="https://img.shields.io/badge/Roles-Admin%20%7C%20Employer%20%7C%20Jobseeker-blueviolet?style=flat-square" alt="Roles"/>
  <img src="https://img.shields.io/badge/UI-Thymeleaf%20%2B%20Bootstrap-blue?style=flat-square" alt="UI"/>
</p>

<h3>💼 A full-stack job portal with employer job postings, jobseeker applications, and live in-app chat</h3>

</div>

---

## 📖 Overview

**Job Portal** is a full-stack recruitment platform built on **Java, Spring Boot, Spring Security, Spring Data JPA (Hibernate), Thymeleaf, and MySQL**. It runs three portals from a single codebase — a public job listing site, an **Employer** dashboard for posting and managing jobs, and a **Jobseeker** dashboard for applying and tracking applications — plus a **real-time WebSocket chat** between employers and jobseekers.

<table align="center">
<tr>
<td align="center">🏗️<br/><b>Architecture</b><br/>Spring MVC (Controller → Service/Repository → JPA)</td>
<td align="center">🔐<br/><b>Auth</b><br/>Spring Security, role-based sessions</td>
<td align="center">💬<br/><b>Realtime</b><br/>WebSocket chat rooms</td>
<td align="center">🎨<br/><b>UI Layer</b><br/>Thymeleaf + Bootstrap + jQuery</td>
</tr>
</table>

## ✨ Features

<table align="center" width="100%">
<tr>
<td width="33%" valign="top">

### 🌐 Public Site
- Home / job listings
- Job search
- Company categories
- Jobseeker sign-up / login

</td>
<td width="33%" valign="top">

### 🏢 Employer Portal
- Create job posts
- Manage / view posted jobs
- View applicants per job
- View jobseeker profiles
- Employer dashboard & profile

</td>
<td width="33%" valign="top">

### 👤 Jobseeker Portal
- Search & browse jobs
- Apply to jobs
- Save jobs for later
- Track applied jobs
- Profile, education & experience

</td>
</tr>
</table>

<div align="center">

### 🛠️ Admin Panel
Manage employers · manage jobseekers · manage job categories · manage employer categories · manage all job posts · dashboard

### 💬 Live Chat
Employers and jobseekers message each other in real time through WebSocket-backed chat rooms

</div>

## 🛠️ Technology Stack

<div align="center">

![My Skills](https://skillicons.dev/icons?i=java,spring,hibernate,mysql,html,css,js,jquery,bootstrap,maven,git,idea)

</div>

| Layer | Technology |
|---|---|
| **Backend** | Java, Spring Boot, Spring MVC, Spring Data JPA (Hibernate), Spring Security, Spring WebSocket |
| **Frontend** | Thymeleaf, Bootstrap, jQuery, DataTables |
| **Database** | MySQL |
| **Build Tool** | Maven |
| **Other** | Google Gson, Lombok |

## 🏗️ Architecture & Workflow

```mermaid
flowchart TD
    A["🌐 Browser<br/>Admin / Employer / Jobseeker"] -->|"HTTP Request"| B["🛡️ Spring Security Filter<br/>(session & role check)"]
    B --> C["🎮 Controller<br/>AdminController / EmployerController / JobseekerController"]
    C --> D["⚙️ Service Layer<br/>LoginsService"]
    C --> E["🗄️ Repository Layer<br/>Spring Data JPA"]
    D --> E
    E --> F[("🐬 MySQL")]
    F --> E
    E --> C
    C -->|"Model + View"| G["🎨 Thymeleaf Templates"]
    G -->|"Rendered HTML"| A

    H["💬 Browser Chat Widget"] <-->|"WebSocket"| I["🔌 chatHandler<br/>WebSocketConfig"]
    I --> F

    style A fill:#0f0c29,stroke:#00d4ff,color:#ffffff
    style B fill:#1a1440,stroke:#ff4d6d,color:#ffffff
    style G fill:#0f0c29,stroke:#2cb67d,color:#ffffff
    style F fill:#1a1440,stroke:#7f5af0,color:#ffffff
    style I fill:#1a1440,stroke:#5EEAD4,color:#ffffff
```

**Request flow:** every request passes through the **Spring Security filter chain**. Controllers delegate to the service/repository layer, JPA/Hibernate talks to MySQL, and Thymeleaf renders the role-appropriate template. Chat runs on a separate **WebSocket** connection handled by `chatHandler`, persisting messages against `Chatroom` / `ChatMessage`.

## 📂 Domain Model

Core entities: `Users`, `Userjobseeker`, `Usercompany`, `Usereducation`, `Userexperience`, `Job`, `Jobapplication`, `Jobcategories`, `Companycategories`, `Savedpost`, `Chatroom`, `ChatMessage`.

`LoginTypes` enum: `ADMIN` · `EMPLOYER` · `JOBSEEKER`

## 🚀 Installation

**1. Clone the repository**
```bash
git clone https://github.com/Nesara29/prajju.git
cd prajju/JobPortal
```

**2. Configure the database**

Create a MySQL database (e.g. `job`) and update `src/main/resources/application.properties`:
```properties
spring.datasource.driverClassName=com.mysql.cj.jdbc.Driver
spring.datasource.url=jdbc:mysql://localhost:3306/job
spring.datasource.username=root
spring.datasource.password=YOUR_DB_PASSWORD
```

**3. Build the project**
```bash
mvn clean install
```

**4. Run the application**
```bash
mvn spring-boot:run
# or
./mvnw spring-boot:run
```

**5. Open your browser**
```
http://localhost:8080
```

## 📂 Project Structure

```
JobPortal/
├── src/
│   ├── main/
│   │   ├── java/com/project/jobportal/
│   │   │   ├── chat/               → WebSocketConfig, chatHandler
│   │   │   ├── config/             → SpringSecurity.java
│   │   │   ├── controller/         → AdminController, EmployerController, JobseekerController
│   │   │   ├── entity/             → Job, Jobapplication, Chatroom, Users, etc.
│   │   │   ├── repository/         → Spring Data JPA repositories
│   │   │   ├── security/           → CustomLoginsDetailsService
│   │   │   ├── service/            → LoginsService (+ impl)
│   │   │   └── jobportalApplication.java
│   │   └── resources/
│   │       ├── templates/
│   │       │   ├── admin/          → dashboard, employers, jobseekers, jobcats, jobs
│   │       │   ├── employer/       → dashboard, createpost, jobs, viewapplied
│   │       │   └── jobseeker/      → dashboard, searchjobs, appliedjobs, savedjobs
│   │       ├── static/assets/      → css, js, images, webfonts
│   │       └── application.properties
│   └── test/
├── uploads/profileimage/           → runtime profile image storage
├── pom.xml
├── mvnw / mvnw.cmd
└── README.md
```

## 🎯 Learning Outcomes

☕ Java & Spring Boot &nbsp;•&nbsp; 🎮 Spring MVC &nbsp;•&nbsp; 🛡️ Spring Security &nbsp;•&nbsp; 🗄️ Spring Data JPA (Hibernate) &nbsp;•&nbsp; 🔌 WebSocket real-time messaging &nbsp;•&nbsp; 🐬 MySQL relational design &nbsp;•&nbsp; 🎨 Thymeleaf templating &nbsp;•&nbsp; 📦 Maven project management

## 🚀 Future Enhancements

<table align="center">
<tr>
<td>📎 Resume Upload (PDF/DOC)</td>
<td>✉️ Email Notifications</td>
<td>🗓️ Interview Scheduling</td>
</tr>
<tr>
<td>🎯 Job Recommendations</td>
<td>📊 Admin Analytics Dashboard</td>
<td>🔗 REST API Integration</td>
</tr>
<tr>
<td>🔑 JWT Authentication</td>
<td>☁️ Cloud Deployment (AWS/Azure)</td>
<td>📱 Mobile Application</td>
</tr>
</table>

## 🤝 Contributing

1. Fork the repository.
2. Create a feature branch.
3. Commit your changes.
4. Push to your branch.
5. Open a Pull Request.

## 👨‍💻 Developer

<div align="center">

| | |
|---|---|
| 🧑‍💻 **Name** | `NESARA` |
| 🐙 **GitHub** | https://github.com/Nesara29 |

</div>

## 🔗 Project Links

<div align="center">

<a href="https://github.com/Nesara29/prajju">
  <img src="https://img.shields.io/badge/Repository-JobPortal-181717?style=for-the-badge&logo=github&logoColor=white" alt="Repository"/>
</a>
<a href="https://github.com/Nesara29/prajju/issues">
  <img src="https://img.shields.io/badge/Report-Issue-red?style=for-the-badge&logo=github&logoColor=white" alt="Issues"/>
</a>
<a href="https://github.com/Nesara29/prajju/fork">
  <img src="https://img.shields.io/badge/Fork-Project-2CB67D?style=for-the-badge&logo=github&logoColor=white" alt="Fork"/>
</a>

</div>

## 📄 License

This project is intended for **educational and learning purposes**. Free to use, modify, and extend for academic or personal projects.

## ⭐ Support

If you found this project useful, please give it a ⭐ **Star** on GitHub.

<img src="https://capsule-render.vercel.app/api?type=waving&color=0:0d1120,50:161d33,100:090C14&height=120&section=footer"/>

</div>
