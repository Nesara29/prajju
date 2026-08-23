<div align="center">

<img src="https://capsule-render.vercel.app/api?type=waving&color=0:090C14,50:161d33,100:0d1120&height=220&section=header&text=ONLINE%20JOB%20PORTAL%20SYSTEM&fontSize=38&fontColor=EDEAE0&animation=fadeIn&fontAlignY=38&desc=Java%20%C2%B7%20Spring%20Boot%20%C2%B7%20Spring%20Security%20%C2%B7%20MySQL&descAlignY=58&descSize=17&descColor=5EEAD4" width="100%"/>

<img src="https://readme-typing-svg.demolab.com?font=Space+Grotesk&size=22&duration=3000&pause=1000&color=C9A227&center=true&vCenter=true&width=600&lines=Employers+post+a+job...;Seekers+find+it...;Everyone+applies+here." alt="Typing SVG"/>

<br/><br/>

<p>
  <img src="https://img.shields.io/badge/Language-Java-ED8B00?style=for-the-badge&logo=openjdk&logoColor=white" alt="Java"/>
  <img src="https://img.shields.io/badge/Framework-Spring%20Boot-6DB33F?style=for-the-badge&logo=springboot&logoColor=white" alt="Spring Boot"/>
  <img src="https://img.shields.io/badge/Database-MySQL-4479A1?style=for-the-badge&logo=mysql&logoColor=white" alt="MySQL"/>
  <img src="https://img.shields.io/badge/Build-Maven-C71A36?style=for-the-badge&logo=apachemaven&logoColor=white" alt="Maven"/>
  <img src="https://img.shields.io/badge/License-Educational%20Use-7F5AF0?style=for-the-badge" alt="License"/>
</p>

<p>
  <img src="https://img.shields.io/badge/Security-Spring%20Security-red?style=flat-square" alt="Spring Security"/>
  <img src="https://img.shields.io/badge/Roles-Employer%20%7C%20Job%20Seeker-blueviolet?style=flat-square" alt="Roles"/>
  <img src="https://img.shields.io/badge/UI-Thymeleaf%20%2B%20HTML5%2FCSS3-blue?style=flat-square" alt="UI Theme"/>
</p>

<h3>💼 A full-stack, role-based online job portal connecting employers and job seekers — built with Java and Spring Boot</h3>

</div>

---

## 📖 Overview

**Online Job Portal System** is a full-stack recruitment web application built on **Java, Spring Boot, Spring Security, Spring Data JPA (Hibernate), Thymeleaf, and MySQL**. It runs two connected portals from a single codebase — an **Employer** side for posting and managing jobs, and a **Job Seeker** side for searching, applying, and tracking applications — all protected by Spring Security authentication.

Employers register, post job openings, and review applicants against each listing. Job seekers register, search and browse open roles, maintain a profile and resume, apply with one click, and track every application from a personal dashboard.

<table align="center">
<tr>
<td align="center">🏗️<br/><b>Architecture</b><br/>Spring MVC (Controller → Service/Repository → JPA)</td>
<td align="center">🔐<br/><b>Auth</b><br/>Spring Security, role-based sessions</td>
<td align="center">🗄️<br/><b>Data Layer</b><br/>Spring Data JPA + Hibernate</td>
<td align="center">🎨<br/><b>UI Layer</b><br/>Thymeleaf + HTML5/CSS3/JS</td>
</tr>
</table>

## ✨ Features

<table align="center" width="100%">
<tr>
<td width="50%" valign="top">

### 🏢 Employer Portal
- Employer registration & login
- Post new job openings
- Edit / manage existing listings
- Review applicants per posting
- Employer dashboard

</td>
<td width="50%" valign="top">

### 👤 Job Seeker Portal
- Job seeker registration & login
- Search & browse open jobs
- One-click apply flow
- Profile & resume management
- Application tracking dashboard

</td>
</tr>
</table>

> 🔑 Access is enforced by **Spring Security** — authenticated sessions gate posting, applying, and dashboard routes by user role.

## 🛠️ Technology Stack

<div align="center">

![My Skills](https://skillicons.dev/icons?i=java,spring,hibernate,mysql,html,css,js,maven,git,idea)

</div>

| Layer | Technology |
|---|---|
| **Backend** | Java, Spring Boot, Spring MVC, Spring Data JPA (Hibernate), Spring Security |
| **Frontend** | Thymeleaf, HTML5, CSS3, JavaScript |
| **Database** | MySQL |
| **Build Tool** | Maven |
| **Tools** | Eclipse / IntelliJ IDEA, Git, GitHub |

## 🏗️ Architecture & Workflow

```mermaid
flowchart TD
    A["🌐 Browser<br/>Employer / Job Seeker"] -->|"HTTP Request"| B["🛡️ Spring Security Filter<br/>(session & role check)"]
    B --> C["🎮 Controller<br/>Spring MVC"]
    C --> D["⚙️ Service Layer"]
    C --> E["🗄️ Repository Layer<br/>Spring Data JPA"]
    D --> E
    E --> F[("🐬 MySQL")]
    F --> E
    E --> C
    C -->|"Model + View"| G["🎨 Thymeleaf Templates"]
    G -->|"Rendered HTML"| A

    style A fill:#0f0c29,stroke:#00d4ff,color:#ffffff
    style B fill:#1a1440,stroke:#ff4d6d,color:#ffffff
    style G fill:#0f0c29,stroke:#2cb67d,color:#ffffff
    style F fill:#1a1440,stroke:#7f5af0,color:#ffffff
```

**Request flow:** every request passes through the **Spring Security filter chain**, which checks the session against the requested route. Controllers delegate to the service/repository layer, JPA/Hibernate talks to MySQL, and Thymeleaf renders the employer or job-seeker template.



## 🚀 Installation & Setup

### 1️⃣ Clone the Repository
```bash
git clone https://github.com/YOUR_USERNAME/OnlineJobPortalSystem.git
```

### 2️⃣ Navigate to the Project
```bash
cd OnlineJobPortalSystem
```

### 3️⃣ Configure the Database
Create a MySQL database and update:
```
src/main/resources/application.properties
```

```properties
spring.datasource.driverClassName=com.mysql.cj.jdbc.Driver
spring.datasource.url=jdbc:mysql://localhost:3306/job_portal
spring.datasource.username=YOUR_DB_USERNAME
spring.datasource.password=YOUR_DB_PASSWORD
```

### 4️⃣ Build the Project
```bash
mvn clean install
```

### 5️⃣ Run the Application
```bash
mvn spring-boot:run
```
or, using the bundled Maven wrapper:
```bash
./mvnw spring-boot:run
```

### 6️⃣ Open the Application
```
http://localhost:8080
```

## 📂 Project Structure

```
OnlineJobPortalSystem/
├── src/
│   ├── main/
│   │   ├── java/
│   │   ├── resources/
│   │   │   ├── templates/
│   │   │   ├── static/
│   │   │   └── application.properties
│   └── test/
├── docs/screenshots/
├── pom.xml
├── mvnw / mvnw.cmd
└── README.md
```

## 🎯 Learning Outcomes

☕ Java & Spring Boot &nbsp;•&nbsp; 🎮 Spring MVC &nbsp;•&nbsp; 🛡️ Spring Security &nbsp;•&nbsp; 🗄️ Spring Data JPA (Hibernate) &nbsp;•&nbsp; 🐬 MySQL relational design &nbsp;•&nbsp; 🎨 Thymeleaf templating &nbsp;•&nbsp; ⚡ CRUD operations end to end &nbsp;•&nbsp; 📦 Maven project management

## 🚀 Future Enhancements

<table align="center">
<tr>
<td>📎 Resume Upload (PDF/DOC)</td>
<td>✉️ Email Notifications</td>
<td>🗓️ Interview Scheduling</td>
</tr>
<tr>
<td>🏢 Company Profiles</td>
<td>🎯 Job Recommendations</td>
<td>📊 Admin Analytics Dashboard</td>
</tr>
<tr>
<td>🔗 REST API Integration</td>
<td>🔑 JWT Authentication</td>
<td>☁️ Cloud Deployment (AWS/Azure)</td>
</tr>
</table>

## 🤝 Contributing

1. 🍴 Fork the repository
2. 🌿 Create a feature branch
3. 💾 Commit your changes
4. 📤 Push your branch
5. 🔁 Submit a Pull Request



## 🔗 Project Links

<div align="center">

<a href="https://github.com/YOUR_USERNAME/OnlineJobPortalSystem">
  <img src="https://img.shields.io/badge/Repository-JobPortal-181717?style=for-the-badge&logo=github&logoColor=white" alt="Repository"/>
</a>
<a href="https://github.com/YOUR_USERNAME/OnlineJobPortalSystem/issues">
  <img src="https://img.shields.io/badge/Report-Issue-red?style=for-the-badge&logo=github&logoColor=white" alt="Issues"/>
</a>
<a href="https://github.com/YOUR_USERNAME/OnlineJobPortalSystem/fork">
  <img src="https://img.shields.io/badge/Fork-Project-2CB67D?style=for-the-badge&logo=github&logoColor=white" alt="Fork"/>
</a>

</div>

## 📄 License

This project is intended for **educational and learning purposes**. Free to use, modify, and extend for academic or personal projects.

## ⭐ Support

If you found this project useful, please give it a ⭐ **Star** on GitHub.

<img src="https://capsule-render.vercel.app/api?type=waving&color=0:0d1120,50:161d33,100:090C14&height=120&section=footer"/>

</div>
