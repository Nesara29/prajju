<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Job Portal — The Entry Point</title>

<link rel="preconnect" href="https://fonts.googleapis.com">
<link href="https://fonts.googleapis.com/css2?family=Space+Grotesk:wght@400;500;600;700&family=Inter:wght@400;500;600&family=JetBrains+Mono:wght@400;500&display=swap" rel="stylesheet">

<style>
  :root{
    --bg-deep:#090C14;
    --bg-panel:#111729;
    --bg-panel-2:#161d33;
    --gold:#C9A227;
    --gold-soft:#e8c766;
    --cyan:#5EEAD4;
    --text:#EDEAE0;
    --muted:#8891A6;
    --line:rgba(237,234,224,0.09);
    --shadow:0 30px 60px -20px rgba(0,0,0,0.6);

    --space-3xs:4px;
    --space-2xs:8px;
    --space-xs:12px;
    --space-sm:16px;
    --space-md:24px;
    --space-lg:32px;
    --space-xl:48px;
    --space-2xl:64px;
    --space-3xl:96px;

    --container-max:1180px;
    --container-pad:clamp(20px, 6vw, 64px);
    --section-y:var(--space-3xl);
  }

  *{
    box-sizing:border-box;
  }

  html{
    scroll-behavior:smooth;
  }

  body{
    margin:0;
    padding:0;
    background:
      radial-gradient(
        ellipse 900px 500px at 50% -10%,
        rgba(201,162,39,0.10),
        transparent 60%
      ),
      radial-gradient(
        ellipse 700px 500px at 90% 20%,
        rgba(94,234,212,0.06),
        transparent 60%
      ),
      var(--bg-deep);

    color:var(--text);
    font-family:'Inter',sans-serif;
    overflow-x:hidden;
    line-height:1.5;
  }

  h1,h2,h3,.display{
    font-family:'Space Grotesk',sans-serif;
    margin:0;
  }

  p{
    margin:0;
  }

  .mono{
    font-family:'JetBrains Mono',monospace;
  }

  a{
    color:inherit;
    text-decoration:none;
  }

  ul{
    margin:0;
    padding:0;
    list-style:none;
  }

  button{
    font-family:inherit;
  }

  @media (prefers-reduced-motion: reduce){
    *{
      animation-duration:0.01ms !important;
      animation-iteration-count:1 !important;
      transition-duration:0.01ms !important;
    }
  }

  /* ---------- SHARED CONTAINER ---------- */

  .container{
    max-width:var(--container-max);
    margin:0 auto;
    padding:0 var(--container-pad);
  }

  /* ---------- NAV ---------- */

  nav{
    position:sticky;
    top:0;
    z-index:50;
    backdrop-filter:blur(10px);
    background:rgba(9,12,20,0.7);
    border-bottom:1px solid var(--line);
  }

  nav .container{
    display:flex;
    align-items:center;
    justify-content:space-between;
    padding-top:var(--space-md);
    padding-bottom:var(--space-md);
  }

  .logo{
    display:flex;
    align-items:center;
    gap:var(--space-2xs);
    font-weight:600;
    font-size:1.05rem;
    letter-spacing:0.02em;
  }

  .logo-mark{
    width:26px;
    height:26px;
    border:2px solid var(--gold);
    border-radius:4px;
    position:relative;
    transform:rotate(45deg);
    flex-shrink:0;
  }

  .logo-mark::after{
    content:'';
    position:absolute;
    inset:5px;
    border:2px solid var(--cyan);
    border-radius:2px;
  }

  .nav-links{
    display:flex;
    gap:var(--space-xl);
    font-size:0.92rem;
    color:var(--muted);
  }

  .nav-links a{
    padding:var(--space-3xs) 0;
  }

  .nav-links a:hover{
    color:var(--text);
  }

  .nav-actions{
    display:flex;
    gap:var(--space-sm);
  }

  .btn{
    padding:var(--space-xs) var(--space-lg);
    border-radius:8px;
    font-size:0.88rem;
    font-weight:600;
    background:linear-gradient(
      180deg,
      var(--gold-soft),
      var(--gold)
    );
    color:#1a1305;
    border:none;
    cursor:pointer;

    box-shadow:
      0 8px 20px -8px rgba(201,162,39,0.6),
      inset 0 1px 0 rgba(255,255,255,0.4);

    transform:translateZ(0);
    transition:
      transform .25s ease,
      box-shadow .25s ease;

    line-height:1;
  }

  .btn:hover{
    transform:translateY(-2px);
    box-shadow:
      0 12px 26px -8px rgba(201,162,39,0.75);
  }

  .btn.ghost{
    background:transparent;
    color:var(--text);
    border:1px solid var(--line);
    box-shadow:none;
  }

  .btn.ghost:hover{
    border-color:var(--gold);
    color:var(--gold-soft);
  }

  /* ---------- HERO ---------- */

  .hero.container{
    display:flex;
    align-items:center;
    justify-content:space-between;
    gap:var(--space-2xl);
    padding-top:var(--space-3xl);
    padding-bottom:var(--space-2xl);
    min-height:78vh;
    perspective:1400px;
  }

  .hero-copy{
    max-width:520px;
    z-index:2;
  }

  .eyebrow{
    font-family:'JetBrains Mono',monospace;
    font-size:0.76rem;
    letter-spacing:0.14em;
    color:var(--cyan);
    text-transform:uppercase;
    margin-bottom:var(--space-md);
    display:flex;
    gap:var(--space-2xs);
    align-items:center;
  }

  .eyebrow::before{
    content:'';
    width:22px;
    height:1px;
    background:var(--cyan);
    flex-shrink:0;
  }

  .hero h1{
    font-size:clamp(2.4rem, 4.6vw, 3.6rem);
    line-height:1.06;
    margin-bottom:var(--space-lg);
    font-weight:700;
    letter-spacing:-0.01em;
  }

  .hero h1 em{
    font-style:normal;
    color:var(--gold-soft);
  }

  .hero p{
    color:var(--muted);
    font-size:1.05rem;
    line-height:1.65;
    margin-bottom:var(--space-2xl);
  }

  .hero-actions{
    display:flex;
    gap:var(--space-sm);
    margin-bottom:var(--space-2xl);
    flex-wrap:wrap;
  }

  .hero-stats{
    display:flex;
    gap:var(--space-lg);
  }

  .hero-stats div{
    border-left:1px solid var(--line);
    padding-left:var(--space-sm);
  }

  .hero-stats b{
    display:block;
    font-size:1.3rem;
    color:var(--text);
    font-family:'Space Grotesk',sans-serif;
    margin-bottom:var(--space-3xs);
  }

  .hero-stats span{
    font-size:0.72rem;
    color:var(--muted);
  }

  /* ---------- 3D PORTAL RING ---------- */

  .portal-stage{
    position:relative;
    width:440px;
    height:440px;
    flex-shrink:0;
    perspective:1600px;
  }

  .portal-ring{
    position:absolute;
    inset:0;
    margin:auto;
    width:190px;
    height:250px;
    transform-style:preserve-3d;
    animation:spin 26s linear infinite;
  }

  .portal-stage:hover .portal-ring{
    animation-play-state:paused;
  }

  @keyframes spin{
    from{
      transform:rotateY(0deg);
    }

    to{
      transform:rotateY(360deg);
    }
  }

  .role-card{
    position:absolute;
    inset:0;
    width:190px;
    height:250px;
    border-radius:14px;
    padding:var(--space-lg) var(--space-md);

    background:
      linear-gradient(
        155deg,
        var(--bg-panel-2),
        var(--bg-panel)
      );

    border:1px solid var(--line);
    box-shadow:var(--shadow);

    display:flex;
    flex-direction:column;
    justify-content:space-between;

    backface-visibility:hidden;
  }

  .role-card .tag{
    font-family:'JetBrains Mono',monospace;
    font-size:0.65rem;
    color:var(--cyan);
    letter-spacing:0.08em;
  }

  .role-card h4{
    margin-top:var(--space-sm);
    margin-bottom:var(--space-3xs);
    font-size:1.02rem;
  }

  .role-card p{
    font-size:0.76rem;
    color:var(--muted);
  }

  .role-card .glyph{
    font-size:1.6rem;
  }

  .portal-glow{
    position:absolute;
    inset:0;
    margin:auto;
    width:300px;
    height:300px;
    border-radius:50%;

    background:
      radial-gradient(
        circle,
        rgba(201,162,39,0.18),
        transparent 70%
      );

    filter:blur(10px);
    z-index:-1;
  }

  .portal-ring2{
    position:absolute;
    inset:0;
    margin:auto;
    width:340px;
    height:340px;
    border:1px dashed rgba(94,234,212,0.25);
    border-radius:50%;
    animation:spin 40s linear infinite reverse;
  }

  /* ---------- SECTION HEADERS ---------- */

  .section{
    padding:var(--section-y) 0;
  }

  .section-head{
    max-width:620px;
    margin:0 auto;
    margin-bottom:var(--space-2xl);
    text-align:center;
  }

  .section-head .eyebrow{
    justify-content:center;
  }

  .section-head h2{
    font-size:clamp(1.8rem,3vw,2.4rem);
    margin-bottom:var(--space-sm);
  }

  .section-head p{
    color:var(--muted);
    font-size:1rem;
    line-height:1.6;
  }

  /* ---------- TILT FEATURE CARDS ---------- */

  .grid{
    display:grid;
    grid-template-columns:
      repeat(auto-fit,minmax(240px,1fr));
    gap:var(--space-lg);
    perspective:1200px;
  }

  .tilt-card{
    background:
      linear-gradient(
        160deg,
        var(--bg-panel-2),
        var(--bg-panel)
      );

    border:1px solid var(--line);
    border-radius:16px;
    padding:var(--space-xl);

    transform-style:preserve-3d;

    transition:
      transform .12s ease,
      border-color .3s ease;

    position:relative;
    overflow:hidden;
  }

  .tilt-card::before{
    content:'';
    position:absolute;
    inset:0;
    opacity:0;

    background:
      linear-gradient(
        120deg,
        rgba(201,162,39,0.12),
        transparent 60%
      );

    transition:opacity .3s ease;
  }

  .tilt-card:hover{
    border-color:rgba(201,162,39,0.4);
  }

  .tilt-card:hover::before{
    opacity:1;
  }

  .tilt-card .num{
    font-family:'JetBrains Mono',monospace;
    color:var(--gold);
    font-size:0.78rem;
    margin-bottom:var(--space-md);
    display:block;
  }

  .tilt-card h3{
    font-size:1.08rem;
    margin-bottom:var(--space-2xs);
  }

  .tilt-card p{
    color:var(--muted);
    font-size:0.9rem;
    line-height:1.55;
  }

  /* ---------- LAYERED STACK ---------- */

  .stack-wrap{
    display:flex;
    justify-content:center;
    padding-top:var(--space-md);
    padding-bottom:var(--space-2xs);
    perspective:1500px;
  }

  .stack{
    position:relative;
    width:340px;
    height:300px;
    transform-style:preserve-3d;
    transform:rotateX(52deg) rotateZ(-38deg);
    transition:transform .5s ease;
  }

  .stack-wrap:hover .stack{
    transform:
      rotateX(48deg)
      rotateZ(-32deg)
      translateY(-6px);
  }

  .layer{
    position:absolute;
    left:0;
    right:0;
    height:64px;
    border-radius:10px;

    border:1px solid rgba(237,234,224,0.14);

    display:flex;
    align-items:center;

    padding:0 var(--space-lg);
    gap:var(--space-sm);

    font-family:'JetBrains Mono',monospace;
    font-size:0.82rem;

    box-shadow:
      0 14px 24px -10px rgba(0,0,0,0.55);
  }

  .layer .dot{
    width:8px;
    height:8px;
    border-radius:50%;
    flex-shrink:0;
  }

  .layer span.small{
    color:var(--muted);
    font-size:0.72rem;
    margin-left:auto;
  }

  .l1{
    top:0px;
    background:linear-gradient(180deg,#1b2340,#141a30);
    z-index:5;
  }

  .l2{
    top:56px;
    background:linear-gradient(180deg,#1a2438,#131c2f);
    z-index:4;
  }

  .l3{
    top:112px;
    background:linear-gradient(180deg,#19223a,#12192c);
    z-index:3;
  }

  .l4{
    top:168px;
    background:linear-gradient(180deg,#182236,#111828);
    z-index:2;
  }

  .l5{
    top:224px;
    background:linear-gradient(180deg,#171f30,#101725);
    z-index:1;
  }

  /* ---------- MODULES LIST ---------- */

  .modules{
    display:grid;
    grid-template-columns:
      repeat(auto-fit,minmax(200px,1fr));

    gap:1px;
    background:var(--line);
    border:1px solid var(--line);
    border-radius:14px;
    overflow:hidden;
  }

  .module{
    background:var(--bg-panel);
    padding:var(--space-lg) var(--space-md);

    transition:
      background .25s ease,
      transform .25s ease;
  }

  .module:hover{
    background:var(--bg-panel-2);
    transform:translateZ(6px);
  }

  .module .glyph{
    font-size:1.3rem;
    margin-bottom:var(--space-sm);
    display:block;
  }

  .module b{
    display:block;
    font-size:0.94rem;
    margin-bottom:var(--space-3xs);
  }

  .module span{
    font-size:0.8rem;
    color:var(--muted);
  }

  /* ---------- CTA ---------- */

  .cta-section{
    padding-bottom:var(--section-y);
  }

  .cta{
    padding:var(--space-3xl) var(--space-2xl);
    border-radius:24px;
    text-align:center;

    background:
      radial-gradient(
        ellipse 500px 260px at 50% 0%,
        rgba(201,162,39,0.16),
        transparent 70%
      ),
      linear-gradient(
        180deg,
        var(--bg-panel-2),
        var(--bg-panel)
      );

    border:1px solid var(--line);
  }

  .cta h2{
    font-size:clamp(1.8rem,3.4vw,2.5rem);
    margin-bottom:var(--space-md);
  }

  .cta p{
    color:var(--muted);
    margin-bottom:var(--space-2xl);
  }

  .cta .hero-actions{
    justify-content:center;
    margin-bottom:0;
  }

  /* ---------- FOOTER ---------- */

  footer{
    border-top:1px solid var(--line);
  }

  footer .container{
    padding-top:var(--space-xl);
    padding-bottom:var(--space-xl);

    display:flex;
    justify-content:space-between;
    align-items:center;

    color:var(--muted);
    font-size:0.85rem;

    flex-wrap:wrap;
    gap:var(--space-md);
  }

  .footer-links{
    display:flex;
    gap:var(--space-lg);
  }

  .footer-links a:hover{
    color:var(--gold-soft);
  }

  /* ---------- RESPONSIVE ---------- */

  @media (max-width:900px){

    .hero.container{
      flex-direction:column;
      text-align:center;
      padding-top:var(--space-2xl);
      gap:var(--space-2xl);
    }

    .hero-copy{
      max-width:100%;
    }

    .hero-actions,
    .hero-stats{
      justify-content:center;
    }

    .portal-stage{
      width:100%;
      max-width:440px;
      height:340px;
    }

    .stack{
      transform:
        rotateX(52deg)
        rotateZ(-38deg)
        scale(0.85);
    }

    :root{
      --section-y:var(--space-2xl);
    }
  }

  @media (max-width:560px){

    .nav-links{
      display:none;
    }

    .hero-stats{
      flex-direction:column;
      gap:var(--space-sm);
    }

    .hero-stats div{
      border-left:none;
      border-top:1px solid var(--line);
      padding-left:0;
      padding-top:var(--space-sm);
    }

    footer .container{
      flex-direction:column;
      text-align:center;
    }
  }

</style>
</head>

<body>

<!-- ================= NAVIGATION ================= -->

<nav>

  <div class="container">

    <div class="logo">
      <span class="logo-mark"></span>
      Job Portal
    </div>

    <div class="nav-links">

      <a href="#stack">
        Stack
      </a>

      <a href="#modules">
        Modules
      </a>

      <a href="#roadmap">
        Roadmap
      </a>

    </div>

    <div class="nav-actions">

      <button class="btn ghost">
        Sign in
      </button>

      <button class="btn">
        Post a job
      </button>

    </div>

  </div>

</nav>


<!-- ================= HERO ================= -->

<section class="hero container">

  <div class="hero-copy">

    <div class="eyebrow">
      Spring Boot &middot; Security &middot; JPA &middot; MySQL
    </div>

    <h1>
      Your career has
      <em>an entry point.</em>
    </h1>

    <p>
      A full-stack job portal built on Java and the Spring ecosystem —
      employers post roles, seekers apply, and Spring Security keeps
      every session honest.
    </p>

    <div class="hero-actions">

      <button class="btn">
        Browse open roles
      </button>

      <button class="btn ghost">
        View on GitHub →
      </button>

    </div>

    <div class="hero-stats">

      <div>
        <b>10+</b>
        <span>core modules</span>
      </div>

      <div>
        <b>100%</b>
        <span>Java &amp; Spring</span>
      </div>

      <div>
        <b>MVC</b>
        <span>architecture</span>
      </div>

    </div>

  </div>


  <!-- 3D PORTAL -->

  <div class="portal-stage">

    <div class="portal-glow"></div>

    <div class="portal-ring2"></div>

    <div
      class="portal-ring"
      id="ring">
    </div>

  </div>

</section>


<!-- ================= MODULES ================= -->

<section
  class="section"
  id="modules">

  <div class="container">

    <div class="section-head">

      <div class="eyebrow">
        What it does
      </div>

      <h2>
        One platform, two sides of the desk
      </h2>

      <p>
        Employers manage openings and applicants.
        Job seekers manage profiles, resumes, and applications.
        Everything meets in one authenticated dashboard.
      </p>

    </div>


    <div class="grid">

      <div class="tilt-card">

        <span class="num">
          01
        </span>

        <h3>
          Secure authentication
        </h3>

        <p>
          Registration and login gated by Spring Security,
          with role-based access for employers and seekers.
        </p>

      </div>


      <div class="tilt-card">

        <span class="num">
          02
        </span>

        <h3>
          Job posting &amp; management
        </h3>

        <p>
          Employers create, edit, and close listings,
          backed by full CRUD over JPA and Hibernate.
        </p>

      </div>


      <div class="tilt-card">

        <span class="num">
          03
        </span>

        <h3>
          Search &amp; browse
        </h3>

        <p>
          Seekers filter and search live openings pulled
          straight from the MySQL-backed catalog.
        </p>

      </div>


      <div class="tilt-card">

        <span class="num">
          04
        </span>

        <h3>
          Applications
        </h3>

        <p>
          One-click apply flow that links a seeker's
          profile to an employer's job posting.
        </p>

      </div>


      <div class="tilt-card">

        <span class="num">
          05
        </span>

        <h3>
          Profile &amp; resume
        </h3>

        <p>
          Seekers maintain a profile that travels
          with every application they submit.
        </p>

      </div>


      <div class="tilt-card">

        <span class="num">
          06
        </span>

        <h3>
          Dashboard
        </h3>

        <p>
          A single home base for tracking postings,
          applicants, and application status.
        </p>

      </div>

    </div>

  </div>

</section>


<!-- ================= STACK ================= -->

<section
  class="section"
  id="stack">

  <div class="container">

    <div class="section-head">

      <div class="eyebrow">
        Under the hood
      </div>

      <h2>
        The Spring stack, layer by layer
      </h2>

      <p>
        Each request moves down through the same five layers —
        from the rendered page to the row in MySQL.
      </p>

    </div>


    <div class="stack-wrap">

      <div class="stack">

        <div class="layer l1">

          <span
            class="dot"
            style="background:var(--gold)">
          </span>

          Thymeleaf + HTML/CSS/JS

          <span class="small">
            view layer
          </span>

        </div>


        <div class="layer l2">

          <span
            class="dot"
            style="background:var(--cyan)">
          </span>

          Spring MVC

          <span class="small">
            controllers
          </span>

        </div>


        <div class="layer l3">

          <span
            class="dot"
            style="background:#f0a3d0">
          </span>

          Spring Security

          <span class="small">
            auth
          </span>

        </div>


        <div class="layer l4">

          <span
            class="dot"
            style="background:#8fb8ff">
          </span>

          Spring Data JPA / Hibernate

          <span class="small">
            ORM
          </span>

        </div>


        <div class="layer l5">

          <span
            class="dot"
            style="background:#7bd88f">
          </span>

          MySQL

          <span class="small">
            storage
          </span>

        </div>

      </div>

    </div>

  </div>

</section>


<!-- ================= ROADMAP ================= -->

<section
  class="section"
  id="roadmap">

  <div class="container">

    <div class="section-head">

      <div class="eyebrow">
        Coming next
      </div>

      <h2>
        Roadmap
      </h2>

      <p>
        The learning project keeps growing —
        these are the modules queued up next.
      </p>

    </div>


    <div class="modules">

      <div class="module">

        <span class="glyph">
          📎
        </span>

        <b>
          Resume upload
        </b>

        <span>
          PDF/DOC attachments
        </span>

      </div>


      <div class="module">

        <span class="glyph">
          ✉️
        </span>

        <b>
          Email notifications
        </b>

        <span>
          status &amp; alerts
        </span>

      </div>


      <div class="module">

        <span class="glyph">
          🗓️
        </span>

        <b>
          Interview scheduling
        </b>

        <span>
          calendar sync
        </span>

      </div>


      <div class="module">

        <span class="glyph">
          🏢
        </span>

        <b>
          Company profiles
        </b>

        <span>
          employer branding
        </span>

      </div>


      <div class="module">

        <span class="glyph">
          🎯
        </span>

        <b>
          Job recommendations
        </b>

        <span>
          matching engine
        </span>

      </div>


      <div class="module">

        <span class="glyph">
          📊
        </span>

        <b>
          Admin analytics
        </b>

        <span>
          hiring insights
        </span>

      </div>


      <div class="module">

        <span class="glyph">
          🔗
        </span>

        <b>
          REST API
        </b>

        <span>
          headless access
        </span>

      </div>


      <div class="module">

        <span class="glyph">
          🔑
        </span>

        <b>
          JWT auth
        </b>

        <span>
          token-based sessions
        </span>

      </div>


      <div class="module">

        <span class="glyph">
          ☁️
        </span>

        <b>
          Cloud deploy
        </b>

        <span>
          AWS / Azure
        </span>

      </div>

    </div>

  </div>

</section>


<!-- ================= CTA ================= -->

<section class="cta-section">

  <div class="container">

    <div class="cta">

      <h2>
        Fork it. Run it. Extend it.
      </h2>

      <p>
        Clone the repo, point it at your MySQL instance,
        and it's live on localhost in minutes.
      </p>

      <div class="hero-actions">

        <button class="btn">
          git clone the repo
        </button>

        <button class="btn ghost">
          Read the docs
        </button>

      </div>

    </div>

  </div>

</section>


<!-- ================= FOOTER ================= -->

<footer>

  <div class="container">

    <div>
      © 2026 Job Portal —
      built for learning enterprise Java.
    </div>

    <div class="footer-links">

      <a href="#">
        GitHub
      </a>

      <a href="#">
        License
      </a>

      <a href="#">
        Contribute
      </a>

    </div>

  </div>

</footer>


<!-- ================= JAVASCRIPT ================= -->

<script>

  const roles = [

    {
      tag:'FULL-TIME',
      title:'Backend Engineer',
      desc:'Java · Spring Boot · JPA',
      glyph:'🛠️'
    },

    {
      tag:'FULL-TIME',
      title:'Frontend Developer',
      desc:'Thymeleaf · HTML/CSS/JS',
      glyph:'🎨'
    },

    {
      tag:'REMOTE',
      title:'DB Administrator',
      desc:'MySQL · Schema design',
      glyph:'🗄️'
    },

    {
      tag:'FULL-TIME',
      title:'Security Engineer',
      desc:'Spring Security · Auth',
      glyph:'🔐'
    },

    {
      tag:'CONTRACT',
      title:'QA Engineer',
      desc:'Testing · CRUD flows',
      glyph:'✅'
    },

    {
      tag:'FULL-TIME',
      title:'Product Designer',
      desc:'Dashboards · UX',
      glyph:'🧭'
    }

  ];


  const ring =
    document.getElementById('ring');


  const n =
    roles.length;


  const radius =
    260;


  roles.forEach((r, i) => {

    const angle =
      (360 / n) * i;


    const card =
      document.createElement('div');


    card.className =
      'role-card';


    card.style.transform =
      `rotateY(${angle}deg) translateZ(${radius}px)`;


    card.innerHTML = `

      <div class="glyph">
        ${r.glyph}
      </div>

      <div>

        <div class="tag">
          ${r.tag}
        </div>

        <h4>
          ${r.title}
        </h4>

        <p>
          ${r.desc}
        </p>

      </div>

    `;


    ring.appendChild(card);

  });

</script>


</body>
</html>
