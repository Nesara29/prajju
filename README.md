<!DOCTYPE html>
<html lang="en" class="dark scroll-smooth">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>NEXUS PLATFORM // Tier-1 to Scaleup Tech Jobs</title>

    <!-- Tailwind CSS CDN -->
    <script src="https://cdn.tailwindcss.com"></script>
    <script>
        tailwind.config = {
            darkMode: 'class',
            theme: {
                extend: {
                    colors: {
                        cyber: {
                            bg: '#050811',
                            card: 'rgba(12, 17, 33, 0.75)',
                            border: 'rgba(255, 255, 255, 0.08)',
                            cyan: '#00f3ff',
                            purple: '#a855f7',
                            emerald: '#10b981',
                            gold: '#fbbf24',
                            nvidia: '#76b900',
                            google: '#ea4335',
                            microsoft: '#00a4ef',
                            stripe: '#635bfc',
                            vercel: '#ffffff',
                            databricks: '#ff3621'
                        }
                    },
                    fontFamily: {
                        sans: ['Inter', 'sans-serif'],
                        mono: ['JetBrains Mono', 'monospace'],
                    },
                    boxShadow: {
                        'glow-cyan': '0 0 20px rgba(0, 243, 255, 0.2)',
                        'glow-purple': '0 0 20px rgba(168, 85, 247, 0.2)',
                        'glow-emerald': '0 0 20px rgba(16, 185, 129, 0.2)',
                    }
                }
            }
        }
    </script>

    <!-- Google Fonts, Lucide Icons, and Three.js -->
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Inter:wght@300;400;500;600;700;800;900&family=JetBrains+Mono:wght@400;500;600&display=swap" rel="stylesheet">
    <script src="https://unpkg.com/lucide@latest"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/three.js/r128/three.min.js"></script>

    <style>
        body {
            background-color: #050811;
            color: #f8fafc;
            font-family: 'Inter', sans-serif;
            overflow-x: hidden;
        }

        #webgl-bg {
            position: fixed;
            top: 0;
            left: 0;
            width: 100vw;
            height: 100vh;
            z-index: 0;
            pointer-events: none;
        }

        .content-layer {
            position: relative;
            z-index: 10;
        }

        .glass-panel {
            background: rgba(12, 17, 33, 0.75);
            backdrop-filter: blur(16px);
            -webkit-backdrop-filter: blur(16px);
            border: 1px solid rgba(255, 255, 255, 0.08);
        }

        .glass-panel-interactive {
            transition: all 0.3s cubic-bezier(0.16, 1, 0.3, 1);
        }

        .glass-panel-interactive:hover {
            border-color: rgba(0, 243, 255, 0.3);
            box-shadow: 0 10px 30px -10px rgba(0, 243, 255, 0.2);
            transform: translateY(-3px);
        }

        .glass-input {
            background: rgba(8, 12, 24, 0.7);
            border: 1px solid rgba(255, 255, 255, 0.1);
            backdrop-filter: blur(10px);
            transition: all 0.25s ease;
        }

        .glass-input:focus {
            outline: none;
            border-color: #00f3ff;
            box-shadow: 0 0 15px rgba(0, 243, 255, 0.25);
            background: rgba(14, 20, 40, 0.9);
        }

        /* Toast Popup Styles */
        .toast-enter {
            transform: translateY(100%);
            opacity: 0;
        }
        .toast-active {
            transform: translateY(0);
            opacity: 1;
            transition: all 0.3s cubic-bezier(0.16, 1, 0.3, 1);
        }

        ::-webkit-scrollbar { width: 6px; height: 6px; }
        ::-webkit-scrollbar-track { background: #050811; }
        ::-webkit-scrollbar-thumb { background: rgba(255, 255, 255, 0.15); border-radius: 3px; }
        ::-webkit-scrollbar-thumb:hover { background: #00f3ff; }
    </style>
</head>
<body class="antialiased selection:bg-cyber-cyan selection:text-black">

    <!-- WebGL Particle Background -->
    <div id="webgl-bg"></div>

    <div class="content-layer">
        
        <!-- Top Announcement Ticker -->
        <div class="bg-gradient-to-r from-slate-950 via-cyber-card to-slate-950 border-b border-white/10 px-4 py-2 text-xs font-mono text-slate-400">
            <div class="max-w-7xl mx-auto flex items-center justify-between overflow-x-auto whitespace-nowrap gap-6">
                <div class="flex items-center gap-3">
                    <span class="inline-flex items-center gap-1.5 px-2 py-0.5 rounded bg-cyber-emerald/10 text-cyber-emerald border border-cyber-emerald/30 font-bold">
                        <span class="w-2 h-2 rounded-full bg-cyber-emerald animate-ping"></span> LIVE MATRIX
                    </span>
                    <span>1,840 Active Roles: <strong class="text-white">Tier 1 Giants (NVIDIA, Google, MS, Meta)</strong> + <strong class="text-cyber-cyan">Scaleups (Stripe, Databricks, Vercel)</strong></span>
                </div>
                <div class="flex items-center gap-4 text-slate-400">
                    <span>Avg Senior TC: <strong class="text-cyber-gold">$340k - $980k</strong></span>
                    <span>•</span>
                    <button onclick="toggleSavedFilter()" class="hover:text-cyber-cyan transition-colors flex items-center gap-1 text-xs">
                        <i data-lucide="bookmark" class="w-3.5 h-3.5 text-cyber-cyan"></i>
                        Saved Roles (<span id="savedCountHeader" class="font-bold text-white">0</span>)
                    </button>
                </div>
            </div>
        </div>

        <!-- Sticky Header & Nav -->
        <header class="sticky top-0 z-40 w-full px-4 lg:px-8 py-3 glass-panel border-b border-white/10">
            <div class="max-w-7xl mx-auto flex items-center justify-between gap-4">
                
                <!-- Logo -->
                <a href="#" onclick="resetFilters()" class="flex items-center gap-3 group">
                    <div class="w-10 h-10 rounded-xl bg-gradient-to-tr from-cyber-cyan via-cyber-purple to-cyber-emerald p-[1px] flex items-center justify-center">
                        <div class="w-full h-full bg-cyber-bg rounded-[11px] flex items-center justify-center">
                            <i data-lucide="cpu" class="w-5 h-5 text-cyber-cyan group-hover:rotate-45 transition-transform duration-300"></i>
                        </div>
                    </div>
                    <div>
                        <span class="font-black text-xl tracking-wider text-white flex items-center gap-1.5">
                            NEXUS <span class="bg-gradient-to-r from-cyber-cyan to-cyber-purple bg-clip-text text-transparent">PLATFORM</span>
                        </span>
                        <span class="text-[10px] font-mono text-slate-400 block -mt-1 tracking-widest">MULTI-TIER TECH JOB ENGINE</span>
                    </div>
                </a>

                <!-- Tier Navigation Pills -->
                <div class="hidden md:flex items-center gap-2 bg-slate-950/80 p-1 rounded-2xl border border-white/10">
                    <button onclick="filterByTier('ALL')" id="tierBtn-ALL" class="tier-pill-btn px-3 py-1.5 rounded-xl text-xs font-mono font-semibold transition-all bg-cyber-cyan/20 text-cyber-cyan border border-cyber-cyan/30">
                        All Companies
                    </button>
                    <button onclick="filterByTier('GIANT')" id="tierBtn-GIANT" class="tier-pill-btn px-3 py-1.5 rounded-xl text-xs font-mono font-semibold transition-all text-slate-400 hover:text-white">
                        ★ Tier 1 Giants
                    </button>
                    <button onclick="filterByTier('SCALEUP')" id="tierBtn-SCALEUP" class="tier-pill-btn px-3 py-1.5 rounded-xl text-xs font-mono font-semibold transition-all text-slate-400 hover:text-white">
                        ⚡ High-Growth Scaleups
                    </button>
                    <button onclick="filterByTier('STARTUP')" id="tierBtn-STARTUP" class="tier-pill-btn px-3 py-1.5 rounded-xl text-xs font-mono font-semibold transition-all text-slate-400 hover:text-white">
                        🚀 Early-Stage Labs
                    </button>
                </div>

                <!-- Action Controls -->
                <div class="flex items-center gap-3">
                    <button onclick="openMatchCalculatorModal()" class="hidden sm:flex items-center gap-2 px-3.5 py-2 rounded-xl glass-panel text-xs font-mono text-cyber-cyan border-cyber-cyan/30 hover:bg-cyber-cyan/10 transition-all">
                        <i data-lucide="calculator" class="w-4 h-4"></i>
                        <span>AI Vector Sim</span>
                    </button>
                    <button onclick="triggerQuickApplyAny()" class="px-5 py-2.5 rounded-xl bg-gradient-to-r from-cyber-cyan via-cyber-purple to-cyber-emerald text-black font-extrabold text-xs uppercase tracking-wider hover:brightness-110 transition-all shadow-glow-cyan">
                        Direct Apply
                    </button>
                </div>
            </div>
        </header>

        <!-- Main Body Workspace -->
        <main class="max-w-7xl mx-auto px-4 lg:px-8 py-8 space-y-10">

            <!-- Hero Section & Search Engine -->
            <section class="space-y-6 text-center pt-2">
                <div class="max-w-4xl mx-auto space-y-3">
                    <div class="inline-flex items-center gap-2 px-4 py-1.5 rounded-full glass-panel border border-cyber-gold/30 text-cyber-gold text-xs font-mono">
                        <i data-lucide="shield-check" class="w-4 h-4 text-cyber-gold"></i>
                        <span>VERIFIED POSITIONS ACROSS TIER-1 GIANTS & FAST-GROWTH TECH</span>
                    </div>
                    <h1 class="text-4xl sm:text-6xl font-black tracking-tight text-white leading-tight">
                        Precision Careers for <br>
                        <span class="bg-gradient-to-r from-cyber-nvidia via-cyber-cyan to-cyber-purple bg-clip-text text-transparent">Systems, AI, Cloud & Full-Stack Engineers</span>
                    </h1>
                    <p class="text-slate-400 text-sm sm:text-base max-w-2xl mx-auto">
                        Explore opportunities with compute cluster allocations, transparent salary bounds ($160k - $950k+), and direct technical team connections.
                    </p>
                </div>

                <!-- Comprehensive Search Bar Console -->
                <div class="glass-panel p-4 sm:p-5 rounded-3xl border-white/10 shadow-2xl space-y-4 max-w-5xl mx-auto text-left">
                    <div class="grid grid-cols-1 md:grid-cols-12 gap-3">
                        
                        <!-- Search Keyword -->
                        <div class="md:col-span-5 relative">
                            <i data-lucide="search" class="w-5 h-5 text-slate-400 absolute left-4 top-3.5"></i>
                            <input type="text" id="searchInput" onkeyup="executeFilter()" placeholder="Search Title, Tech Stack (CUDA, PyTorch, React, Java, Rust)..." class="w-full glass-input text-sm text-white placeholder-slate-500 pl-12 pr-4 py-3 rounded-2xl">
                        </div>

                        <!-- Company Select Dropdown -->
                        <div class="md:col-span-3 relative">
                            <i data-lucide="building-2" class="w-5 h-5 text-slate-400 absolute left-4 top-3.5"></i>
                            <select id="companyDropdown" onchange="executeFilter()" class="w-full glass-input text-sm text-white pl-12 pr-8 py-3 rounded-2xl appearance-none cursor-pointer">
                                <option value="ALL" class="bg-slate-900">All Tech Companies</option>
                                <optgroup label="Tier 1 Tech Giants" class="bg-slate-900 font-bold text-cyber-cyan">
                                    <option value="NVIDIA" class="bg-slate-900">NVIDIA Corporation</option>
                                    <option value="Google" class="bg-slate-900">Google / DeepMind</option>
                                    <option value="Microsoft" class="bg-slate-900">Microsoft Research / Azure</option>
                                    <option value="Meta" class="bg-slate-900">Meta FAIR & Reality Labs</option>
                                    <option value="Apple" class="bg-slate-900">Apple Silicon & AI</option>
                                    <option value="Amazon" class="bg-slate-900">Amazon Web Services (AWS)</option>
                                </optgroup>
                                <optgroup label="High-Growth Scaleups" class="bg-slate-900 font-bold text-cyber-purple">
                                    <option value="Stripe" class="bg-slate-900">Stripe Core Payments</option>
                                    <option value="Databricks" class="bg-slate-900">Databricks Distributed Systems</option>
                                    <option value="Vercel" class="bg-slate-900">Vercel Edge & Compute</option>
                                    <option value="Figma" class="bg-slate-900">Figma WebGL Engine</option>
                                    <option value="Midjourney" class="bg-slate-900">Midjourney Generative AI</option>
                                    <option value="Postman" class="bg-slate-900">Postman API Platform</option>
                                    <option value="Supabase" class="bg-slate-900">Supabase Cloud Database</option>
                                </optgroup>
                                <optgroup label="Early-Stage Innovation Labs" class="bg-slate-900 font-bold text-cyber-emerald">
                                    <option value="Robotics Lab" class="bg-slate-900">Kinetix Robotics (Series A)</option>
                                    <option value="Agentic AI" class="bg-slate-900">Synapse Agentic Labs</option>
                                </optgroup>
                            </select>
                            <i data-lucide="chevron-down" class="w-4 h-4 text-slate-400 absolute right-4 top-4 pointer-events-none"></i>
                        </div>

                        <!-- Minimum Salary Dropdown -->
                        <div class="md:col-span-2 relative">
                            <i data-lucide="dollar-sign" class="w-5 h-5 text-slate-400 absolute left-4 top-3.5"></i>
                            <select id="salaryDropdown" onchange="executeFilter()" class="w-full glass-input text-sm text-white pl-10 pr-8 py-3 rounded-2xl appearance-none cursor-pointer">
                                <option value="0" class="bg-slate-900">Min Salary (Any)</option>
                                <option value="200" class="bg-slate-900">$200k+ / yr</option>
                                <option value="350" class="bg-slate-900">$350k+ / yr</option>
                                <option value="500" class="bg-slate-900">$500k+ / yr</option>
                            </select>
                            <i data-lucide="chevron-down" class="w-4 h-4 text-slate-400 absolute right-4 top-4 pointer-events-none"></i>
                        </div>

                        <!-- Reset / Search Button -->
                        <div class="md:col-span-2 flex gap-2">
                            <button onclick="executeFilter()" class="w-full py-3 bg-gradient-to-r from-cyber-cyan to-cyber-emerald text-black font-extrabold text-sm rounded-2xl flex items-center justify-center gap-2 hover:brightness-110 transition-all shadow-glow-cyan">
                                <i data-lucide="filter" class="w-4 h-4"></i>
                                <span>Filter</span>
                            </button>
                        </div>
                    </div>

                    <!-- Category Pills & Interactive Tags -->
                    <div class="flex flex-wrap items-center justify-between gap-4 pt-3 border-t border-white/5">
                        <div class="flex flex-wrap items-center gap-2 text-xs font-mono">
                            <span class="text-slate-500 mr-1">STACK FOCUS:</span>
                            <button onclick="toggleTagFilter('AI/ML')" id="tag-AI/ML" class="tag-btn px-3 py-1 rounded-xl bg-slate-900 text-slate-400 border border-white/10 hover:border-cyber-cyan transition-all">⚡ AI & Machine Learning</button>
                            <button onclick="toggleTagFilter('Systems')" id="tag-Systems" class="tag-btn px-3 py-1 rounded-xl bg-slate-900 text-slate-400 border border-white/10 hover:border-cyber-purple transition-all">⚙️ Systems / CUDA / Kernel</button>
                            <button onclick="toggleTagFilter('Web')" id="tag-Web" class="tag-btn px-3 py-1 rounded-xl bg-slate-900 text-slate-400 border border-white/10 hover:border-cyber-emerald transition-all">🌐 Full-Stack & Cloud</button>
                            <button onclick="toggleTagFilter('Remote')" id="tag-Remote" class="tag-btn px-3 py-1 rounded-xl bg-slate-900 text-slate-400 border border-white/10 hover:border-cyber-gold transition-all">🌐 100% Remote Only</button>
                        </div>

                        <!-- Dynamic Sorting Control -->
                        <div class="flex items-center gap-2 text-xs font-mono">
                            <span class="text-slate-400">Sort By:</span>
                            <select id="sortSelect" onchange="executeFilter()" class="bg-slate-950 text-cyber-cyan font-bold border border-white/10 rounded-xl px-3 py-1.5 text-xs focus:outline-none">
                                <option value="MATCH">Highest AI Match %</option>
                                <option value="SALARY_HIGH">Salary: High to Low</option>
                                <option value="RATING">5-Star Rating</option>
                            </select>
                        </div>
                    </div>
                </div>
            </section>

            <!-- Company Spectrum Showcase (Giants to Scaleups) -->
            <section class="space-y-4">
                <div class="flex items-center justify-between">
                    <div>
                        <h2 class="text-xl font-bold text-white flex items-center gap-2">
                            <span>Featured Tech Ecosystem</span>
                            <span class="text-xs font-mono px-2.5 py-0.5 rounded-full bg-cyber-emerald/10 text-cyber-emerald border border-cyber-emerald/30">15 VERIFIED EMPLOYERS</span>
                        </h2>
                        <p class="text-xs text-slate-400">Select any logo to isolate current engineering requisitions</p>
                    </div>
                    <button onclick="resetFilters()" class="text-xs font-mono text-cyber-cyan hover:underline">Reset All Filters</button>
                </div>

                <!-- Interactive Logos Row -->
                <div class="grid grid-cols-2 sm:grid-cols-4 md:grid-cols-6 lg:grid-cols-8 gap-3">
                    <button onclick="quickFilterCompany('NVIDIA')" class="glass-panel p-3 rounded-2xl flex flex-col items-center justify-center gap-1.5 hover:border-cyber-nvidia/50 transition-all group">
                        <div class="w-8 h-8 rounded-xl bg-cyber-nvidia/10 border border-cyber-nvidia/30 text-cyber-nvidia font-mono font-black flex items-center justify-center text-sm group-hover:scale-110 transition-transform">NV</div>
                        <span class="text-xs font-bold text-white">NVIDIA</span>
                        <span class="text-[10px] text-cyber-nvidia font-mono">Tier 1 Giant</span>
                    </button>

                    <button onclick="quickFilterCompany('Google')" class="glass-panel p-3 rounded-2xl flex flex-col items-center justify-center gap-1.5 hover:border-cyber-google/50 transition-all group">
                        <div class="w-8 h-8 rounded-xl bg-cyber-google/10 border border-cyber-google/30 text-cyber-google font-mono font-black flex items-center justify-center text-sm group-hover:scale-110 transition-transform">G</div>
                        <span class="text-xs font-bold text-white">DeepMind</span>
                        <span class="text-[10px] text-cyber-google font-mono">Tier 1 Giant</span>
                    </button>

                    <button onclick="quickFilterCompany('Microsoft')" class="glass-panel p-3 rounded-2xl flex flex-col items-center justify-center gap-1.5 hover:border-cyber-microsoft/50 transition-all group">
                        <div class="w-8 h-8 rounded-xl bg-cyber-microsoft/10 border border-cyber-microsoft/30 text-cyber-microsoft font-mono font-black flex items-center justify-center text-sm group-hover:scale-110 transition-transform">MS</div>
                        <span class="text-xs font-bold text-white">Microsoft</span>
                        <span class="text-[10px] text-cyber-microsoft font-mono">Tier 1 Giant</span>
                    </button>

                    <button onclick="quickFilterCompany('Meta')" class="glass-panel p-3 rounded-2xl flex flex-col items-center justify-center gap-1.5 hover:border-purple-500/50 transition-all group">
                        <div class="w-8 h-8 rounded-xl bg-purple-500/10 border border-purple-500/30 text-purple-400 font-mono font-black flex items-center justify-center text-sm group-hover:scale-110 transition-transform">∞</div>
                        <span class="text-xs font-bold text-white">Meta FAIR</span>
                        <span class="text-[10px] text-purple-400 font-mono">Tier 1 Giant</span>
                    </button>

                    <button onclick="quickFilterCompany('Stripe')" class="glass-panel p-3 rounded-2xl flex flex-col items-center justify-center gap-1.5 hover:border-cyber-stripe/50 transition-all group">
                        <div class="w-8 h-8 rounded-xl bg-cyber-stripe/10 border border-cyber-stripe/30 text-cyber-stripe font-mono font-black flex items-center justify-center text-sm group-hover:scale-110 transition-transform">S</div>
                        <span class="text-xs font-bold text-white">Stripe</span>
                        <span class="text-[10px] text-cyber-stripe font-mono">Scaleup</span>
                    </button>

                    <button onclick="quickFilterCompany('Databricks')" class="glass-panel p-3 rounded-2xl flex flex-col items-center justify-center gap-1.5 hover:border-cyber-databricks/50 transition-all group">
                        <div class="w-8 h-8 rounded-xl bg-cyber-databricks/10 border border-cyber-databricks/30 text-cyber-databricks font-mono font-black flex items-center justify-center text-sm group-hover:scale-110 transition-transform">DB</div>
                        <span class="text-xs font-bold text-white">Databricks</span>
                        <span class="text-[10px] text-cyber-databricks font-mono">Scaleup</span>
                    </button>

                    <button onclick="quickFilterCompany('Vercel')" class="glass-panel p-3 rounded-2xl flex flex-col items-center justify-center gap-1.5 hover:border-white/50 transition-all group">
                        <div class="w-8 h-8 rounded-xl bg-white/10 border border-white/30 text-white font-mono font-black flex items-center justify-center text-sm group-hover:scale-110 transition-transform">▲</div>
                        <span class="text-xs font-bold text-white">Vercel</span>
                        <span class="text-[10px] text-slate-300 font-mono">Scaleup</span>
                    </button>

                    <button onclick="quickFilterCompany('Midjourney')" class="glass-panel p-3 rounded-2xl flex flex-col items-center justify-center gap-1.5 hover:border-cyber-cyan/50 transition-all group">
                        <div class="w-8 h-8 rounded-xl bg-cyber-cyan/10 border border-cyber-cyan/30 text-cyber-cyan font-mono font-black flex items-center justify-center text-sm group-hover:scale-110 transition-transform">MJ</div>
                        <span class="text-xs font-bold text-white">Midjourney</span>
                        <span class="text-[10px] text-cyber-cyan font-mono">AI Research</span>
                    </button>
                </div>
            </section>

            <!-- Main Job Listings Results Grid -->
            <section class="space-y-6">
                <div class="flex flex-col sm:flex-row sm:items-center justify-between gap-4 border-b border-white/10 pb-4">
                    <div>
                        <h2 class="text-2xl font-bold text-white flex items-center gap-3">
                            <span>Open Engineering Positions</span>
                            <span id="resultsCountBadge" class="text-xs font-mono px-3 py-1 rounded-full bg-cyber-cyan/10 text-cyber-cyan border border-cyber-cyan/30">12 Positions Found</span>
                        </h2>
                        <p class="text-xs text-slate-400">Click any card to expand full technical stack telemetry & team details</p>
                    </div>

                    <div class="flex items-center gap-3 text-xs font-mono text-slate-400">
                        <span class="flex items-center gap-1.5"><span class="w-2.5 h-2.5 rounded-full bg-cyber-emerald"></span> Verified Compute Budget</span>
                        <span class="flex items-center gap-1.5"><span class="w-2.5 h-2.5 rounded-full bg-cyber-gold"></span> Executive Compensation</span>
                    </div>
                </div>

                <!-- Dynamic Jobs Grid Container -->
                <div id="jobsGrid" class="grid grid-cols-1 md:grid-cols-2 gap-6">
                    <!-- Cards are dynamically generated via JavaScript -->
                </div>
            </section>
        </main>

        <!-- Footer -->
        <footer class="border-t border-white/10 mt-20 py-10 px-6 glass-panel">
            <div class="max-w-7xl mx-auto flex flex-col sm:flex-row items-center justify-between gap-6 text-slate-500 text-xs font-mono">
                <div class="flex items-center gap-3">
                    <div class="w-8 h-8 rounded-lg bg-cyber-cyan/10 border border-cyber-cyan/30 flex items-center justify-center text-cyber-cyan font-bold">N</div>
                    <span>NEXUS JOB PLATFORM ENGINE &copy; 2026. TIER-1 TO SCALEUP CAREER VECTORS.</span>
                </div>
                <div class="flex items-center gap-6">
                    <a href="#" onclick="alert('NVIDIA Compute API Key Status: Connected (H100 Node Array)')" class="hover:text-cyber-cyan transition-colors">COMPUTE MATRIX</a>
                    <a href="#" onclick="alert('Neural Resume Proof On-Chain: Verified')" class="hover:text-cyber-cyan transition-colors">PROOF-OF-SKILL</a>
                    <a href="#" onclick="alert('Security Level: Quantum Enclave Active')" class="hover:text-cyber-cyan transition-colors">ENCLAVE AUTH</a>
                </div>
            </div>
        </footer>
    </div>

    <!-- APPLY MODAL DRAWER -->
    <div id="applyModal" class="fixed inset-0 z-50 bg-black/80 backdrop-blur-md hidden flex items-center justify-center p-4">
        <div class="glass-panel w-full max-w-xl p-6 sm:p-8 rounded-3xl border-cyber-cyan/30 space-y-6 relative animate-in fade-in zoom-in duration-200">
            <button onclick="closeApplyModal()" class="absolute top-6 right-6 text-slate-400 hover:text-white">
                <i data-lucide="x" class="w-6 h-6"></i>
            </button>

            <div>
                <span class="text-xs font-mono text-cyber-cyan uppercase tracking-widest block mb-1">DIRECT SYNAPSE APPLICATION</span>
                <h3 id="modalJobTitle" class="text-2xl font-bold text-white">Senior CUDA Systems Engineer</h3>
                <p id="modalCompanyInfo" class="text-xs font-mono text-cyber-nvidia mt-1">NVIDIA Corporation • $450,000 - $750,000 / yr</p>
            </div>

            <form onsubmit="handleApplicationSubmit(event)" class="space-y-4">
                <div>
                    <label class="text-xs font-mono text-slate-300 block mb-1">Candidate Full Name</label>
                    <input type="text" id="applicantName" required class="w-full glass-input text-sm text-white px-4 py-3 rounded-xl" placeholder="Dr. Elena Rostova">
                </div>

                <div class="grid grid-cols-1 sm:grid-cols-2 gap-4">
                    <div>
                        <label class="text-xs font-mono text-slate-300 block mb-1">Direct Email Address</label>
                        <input type="email" id="applicantEmail" required class="w-full glass-input text-sm text-white px-4 py-3 rounded-xl" placeholder="elena@stanford.edu">
                    </div>
                    <div>
                        <label class="text-xs font-mono text-slate-300 block mb-1">GitHub / Code Portfolio URL</label>
                        <input type="url" id="applicantGithub" required class="w-full glass-input text-sm text-white px-4 py-3 rounded-xl" placeholder="https://github.com/erostova">
                    </div>
                </div>

                <div>
                    <label class="text-xs font-mono text-slate-300 block mb-1">Core Tech Stack Vector Alignment</label>
                    <textarea rows="3" id="applicantSkills" class="w-full glass-input text-xs font-mono text-white p-3 rounded-xl" placeholder="CUDA 12.4, PyTorch FSDP, C++20, Distributed Systems, Docker, React, Java..."></textarea>
                </div>

                <div class="pt-2 flex gap-3">
                    <button type="button" onclick="closeApplyModal()" class="w-1/3 py-3.5 glass-panel text-slate-400 font-bold text-xs rounded-xl uppercase tracking-wider hover:text-white">
                        Cancel
                    </button>
                    <button type="submit" class="w-2/3 py-3.5 bg-gradient-to-r from-cyber-cyan via-cyber-purple to-cyber-emerald text-black font-extrabold text-xs rounded-xl uppercase tracking-wider hover:brightness-110 transition-all shadow-glow-cyan">
                        Submit Application Vector
                    </button>
                </div>
            </form>
        </div>
    </div>

    <!-- AI MATCH CALCULATOR MODAL -->
    <div id="matchCalcModal" class="fixed inset-0 z-50 bg-black/80 backdrop-blur-md hidden flex items-center justify-center p-4">
        <div class="glass-panel w-full max-w-lg p-6 sm:p-8 rounded-3xl border-cyber-purple/30 space-y-6 relative">
            <button onclick="closeMatchCalcModal()" class="absolute top-6 right-6 text-slate-400 hover:text-white">
                <i data-lucide="x" class="w-6 h-6"></i>
            </button>

            <div class="flex items-center gap-3">
                <div class="w-12 h-12 rounded-2xl bg-cyber-purple/10 text-cyber-purple border border-cyber-purple/30 flex items-center justify-center font-bold">
                    <i data-lucide="calculator" class="w-6 h-6"></i>
                </div>
                <div>
                    <h3 class="text-xl font-bold text-white">AI Vector Match Simulator</h3>
                    <p class="text-xs font-mono text-slate-400">Calculates candidate alignment score across target employers</p>
                </div>
            </div>

            <div class="space-y-4">
                <div>
                    <div class="flex justify-between text-xs font-mono mb-1">
                        <span class="text-slate-300">Years of High-Performance Experience</span>
                        <span id="expValSim" class="text-cyber-cyan font-bold">6 Years</span>
                    </div>
                    <input type="range" id="expSliderSim" min="1" max="15" value="6" oninput="runSimCalc()" class="w-full accent-cyber-cyan cursor-pointer">
                </div>

                <div>
                    <div class="flex justify-between text-xs font-mono mb-1">
                        <span class="text-slate-300">Target Tech Stack Proficiency (CUDA/JAX/Rust/React)</span>
                        <span id="stackValSim" class="text-cyber-purple font-bold">92%</span>
                    </div>
                    <input type="range" id="stackSliderSim" min="50" max="100" value="92" oninput="runSimCalc()" class="w-full accent-cyber-purple cursor-pointer">
                </div>
            </div>

            <div class="p-4 rounded-2xl bg-slate-950 border border-white/10 space-y-2 font-mono text-xs">
                <div class="flex justify-between text-slate-300">
                    <span>NVIDIA AI Match Index:</span>
                    <strong id="simNvMatch" class="text-cyber-nvidia font-bold">96.4%</strong>
                </div>
                <div class="flex justify-between text-slate-300">
                    <span>Google DeepMind Match Index:</span>
                    <strong id="simGglMatch" class="text-cyber-google font-bold">94.1%</strong>
                </div>
                <div class="flex justify-between text-slate-300">
                    <span>Stripe Infrastructure Match Index:</span>
                    <strong id="simStripeMatch" class="text-cyber-stripe font-bold">97.8%</strong>
                </div>
            </div>

            <button onclick="closeMatchCalcModal()" class="w-full py-3 bg-cyber-purple text-black font-extrabold text-xs rounded-xl uppercase tracking-wider">
                Close Simulator
            </button>
        </div>
    </div>

    <!-- NOTIFICATION TOAST CONTAINER -->
    <div id="toastContainer" class="fixed bottom-6 right-6 z-50 space-y-3 pointer-events-none"></div>

    <!-- MAIN JAVASCRIPT LOGIC -->
    <script>
        // MASTER COMPREHENSIVE JOB DATABASE (Giants to Startups)
        const masterJobs = [
            {
                id: "nv-01",
                tier: "GIANT",
                company: "NVIDIA",
                companyColor: "cyber-nvidia",
                logoText: "NV",
                title: "Senior CUDA Systems Engineer",
                location: "Santa Clara, CA • Remote Eligible",
                salaryVal: 520,
                salaryDisplay: "$450,000 - $720,000 / yr + RSUs",
                rating: 5.0,
                matchScore: 98,
                category: "Systems",
                isRemote: true,
                tags: ["CUDA", "C++", "PyTorch", "GPU Acceleration", "Remote"],
                overview: "Architect next-generation CUDA 12.x primitives and memory kernels for Blackwell GPU compute clusters.",
                details: {
                    team: "NVIDIA Acceleration Architecture Team",
                    compute: "Uncapped H100/H200 Supercomputer Access",
                    requirements: ["7+ years C++ / CUDA programming", "Deep understanding of GPU warp scheduling and shared memory", "Proven experience optimizing LLM inference latency"],
                    stack: ["CUDA 12.4", "C++20", "PyTorch", "TensorRT", "Linux Kernel"]
                }
            },
            {
                id: "ggl-01",
                tier: "GIANT",
                company: "Google",
                companyColor: "cyber-google",
                logoText: "G",
                title: "DeepMind Staff Research Scientist - Gemini 2.5",
                location: "Mountain View, CA • Hybrid",
                salaryVal: 620,
                salaryDisplay: "$480,000 - $850,000 / yr + Equity",
                rating: 4.95,
                matchScore: 96,
                category: "AI/ML",
                isRemote: false,
                tags: ["AI/ML", "JAX", "TPU", "Python", "LLMs"],
                overview: "Lead foundational research into multimodal Gemini 2.5 agent reasoning and exascale distributed TPU pod training.",
                details: {
                    team: "Google DeepMind Foundation Models",
                    compute: "Direct Pod Access (TPU v5p Arrays)",
                    requirements: ["Ph.D. or equivalent research impact in Machine Learning", "Expertise in JAX, XLA, and TPU cluster topologies", "Track record of publications in NeurIPS/ICML"],
                    stack: ["JAX", "Python", "TPU v5p", "C++", "TensorFlow"]
                }
            },
            {
                id: "ms-01",
                tier: "GIANT",
                company: "Microsoft",
                companyColor: "cyber-microsoft",
                logoText: "MS",
                title: "Principal Azure Distributed Systems Architect",
                location: "Redmond, WA • 100% Remote",
                salaryVal: 490,
                salaryDisplay: "$380,000 - $680,000 / yr + Bonus",
                rating: 4.92,
                matchScore: 95,
                category: "Web",
                isRemote: true,
                tags: ["Systems", "Rust", "C++", "Azure", "Distributed", "Remote"],
                overview: "Build high-throughput global cloud fabrics powering OpenAI supercomputing workloads across Azure data centers.",
                details: {
                    team: "Azure Core High-Performance Computing",
                    compute: "Exascale Azure NDv5 Virtual Machines",
                    requirements: ["8+ years in large-scale distributed systems", "High proficiency in Rust or C++ and RDMA networking", "Experience with fault-tolerant cloud storage engines"],
                    stack: ["Rust", "C++", "Azure HPC", "Linux", "Kubernetes"]
                }
            },
            {
                id: "meta-01",
                tier: "GIANT",
                company: "Meta",
                companyColor: "purple-400",
                logoText: "∞",
                title: "L6 Graphics Systems Engineer - Reality Labs",
                location: "Burlingame, CA",
                salaryVal: 540,
                salaryDisplay: "$420,000 - $780,000 / yr",
                rating: 4.9,
                matchScore: 93,
                category: "Systems",
                isRemote: false,
                tags: ["Systems", "C++", "Vulkan", "WebGL", "Realtime"],
                overview: "Pioneer low-latency neural rendering pipelines and foveated graphics engines for Quest 4 hardware.",
                details: {
                    team: "Meta Reality Labs Graphics Core",
                    compute: "Custom Silicon Prototyping Hardware",
                    requirements: ["Expertise in C++20, Vulkan, and custom shader pipelines", "Background in real-time ray tracing and spatial computing"],
                    stack: ["C++20", "Vulkan", "HLSL", "Python", "Spatial Rendering"]
                }
            },
            {
                id: "strp-01",
                tier: "SCALEUP",
                company: "Stripe",
                companyColor: "cyber-stripe",
                logoText: "S",
                title: "Staff Infrastructure Engineer - Payments Core",
                location: "San Francisco, CA • Remote Eligible",
                salaryVal: 460,
                salaryDisplay: "$360,000 - $620,000 / yr + Stock",
                rating: 4.94,
                matchScore: 97,
                category: "Web",
                isRemote: true,
                tags: ["Web", "Java", "Ruby", "SQL Server", "Docker", "Remote"],
                overview: "Architect zero-downtime, sub-millisecond payment processing engines handling over $1 Trillion in global volume.",
                details: {
                    team: "Stripe Core Transaction Pipeline",
                    compute: "Multi-Region Distributed Database Arrays",
                    requirements: ["Strong background in Java / Ruby backend architectures", "Deep mastery of SQL Server / PostgreSQL transaction isolation", "Containerized deployments via Docker & Kubernetes"],
                    stack: ["Java", "Ruby", "SQL Server", "Docker", "AWS", "Kafka"]
                }
            },
            {
                id: "db-01",
                tier: "SCALEUP",
                company: "Databricks",
                companyColor: "cyber-databricks",
                logoText: "DB",
                title: "Lead Distributed Engine Performance Engineer",
                location: "San Francisco, CA • Hybrid",
                salaryVal: 510,
                salaryDisplay: "$400,000 - $710,000 / yr",
                rating: 4.91,
                matchScore: 94,
                category: "Systems",
                isRemote: false,
                tags: ["Systems", "C++", "Java", "Spark", "Distributed"],
                overview: "Optimize the Photon C++ vectorized query engine for hyper-scale lakehouse data processing workloads.",
                details: {
                    team: "Databricks Core Execution Engine",
                    compute: "10,000+ Node Distributed Clusters",
                    requirements: ["Proven experience in C++ and Java SIMD vectorization", "In-depth understanding of database query optimizers"],
                    stack: ["C++", "Java", "Apache Spark", "Scala", "SIMD"]
                }
            },
            {
                id: "vrc-01",
                tier: "SCALEUP",
                company: "Vercel",
                companyColor: "white",
                logoText: "▲",
                title: "Principal Edge Runtime Engineer",
                location: "Remote (Global)",
                salaryVal: 420,
                salaryDisplay: "$340,000 - $550,000 / yr + Equity",
                rating: 4.96,
                matchScore: 96,
                category: "Web",
                isRemote: true,
                tags: ["Web", "Rust", "WebAssembly", "React", "TypeScript", "Remote"],
                overview: "Build the next generation of global WebAssembly edge function runners powering Next.js applications.",
                details: {
                    team: "Vercel Infrastructure & Edge Compute",
                    compute: "100+ Anycast Global Edge Nodes",
                    requirements: ["Extensive experience in Rust and V8 isolates", "Deep knowledge of TypeScript, React internals, and WebAssembly"],
                    stack: ["Rust", "TypeScript", "React", "WebAssembly", "V8 Engine"]
                }
            },
            {
                id: "mj-01",
                tier: "SCALEUP",
                company: "Midjourney",
                companyColor: "cyber-cyan",
                logoText: "MJ",
                title: "Senior Generative AI Core Lead",
                location: "San Francisco, CA • Hybrid",
                salaryVal: 650,
                salaryDisplay: "$500,000 - $950,000 / yr",
                rating: 5.0,
                matchScore: 99,
                category: "AI/ML",
                isRemote: false,
                tags: ["AI/ML", "PyTorch", "CUDA", "Python", "Diffusion"],
                overview: "Spearhead state-of-the-art visual diffusion architecture research and multi-GPU neural rendering models.",
                details: {
                    team: "Midjourney Generative Core",
                    compute: "Dedicated High-Bandwidth GPU Clusters",
                    requirements: ["Extensive experience in PyTorch and CUDA custom operators", "Top-tier contributions to diffusion or autoregressive models"],
                    stack: ["PyTorch", "Python", "CUDA", "C++", "Custom Kernels"]
                }
            },
            {
                id: "fig-01",
                tier: "SCALEUP",
                company: "Figma",
                companyColor: "cyber-purple",
                logoText: "F",
                title: "Web Engine & GPU Rendering Engineer",
                location: "San Francisco, CA • Remote Eligible",
                salaryVal: 440,
                salaryDisplay: "$350,000 - $580,000 / yr",
                rating: 4.93,
                matchScore: 92,
                category: "Web",
                isRemote: true,
                tags: ["Web", "C++", "WebGL", "TypeScript", "React", "Remote"],
                overview: "Engineers the high-performance WebGL / WebGPU canvas rendering engine for collaborative real-time vector graphics.",
                details: {
                    team: "Figma Canvas Engine",
                    compute: "In-Browser GPU Acceleration Frameworks",
                    requirements: ["Deep understanding of C++ compiled to WebAssembly", "Expertise in WebGL, WebGPU, and React interface layers"],
                    stack: ["C++", "WebGL", "TypeScript", "React", "Wasm"]
                }
            },
            {
                id: "bot-01",
                tier: "STARTUP",
                company: "Kinetix Robotics",
                companyColor: "cyber-emerald",
                logoText: "KR",
                title: "Embedded Robotics & Sim-to-Real Lead",
                location: "Austin, TX • On-site Lab",
                salaryVal: 380,
                salaryDisplay: "$280,000 - $450,000 / yr + 1.5% Equity",
                rating: 4.88,
                matchScore: 89,
                category: "Systems",
                isRemote: false,
                tags: ["Systems", "C++", "Python", "ROS2", "Robotics"],
                overview: "Deploy real-time neural motor control policies on humanoid bipedal hardware using ROS2 and C++.",
                details: {
                    team: "Kinetix Autonomous Hardware",
                    compute: "Direct On-Robot Jetson Orin Compute",
                    requirements: ["Mastery of ROS2, C++, and Python kinematics", "Experience deploying sim-to-real Isaac Gym policies"],
                    stack: ["C++20", "ROS2", "Python", "CUDA", "Linux Realtime"]
                }
            }
        ];

        // STATE MANAGEMENT VARIABLES
        let currentTier = 'ALL';
        let currentTag = null;
        let savedJobIds = new Set();
        let expandedCardIds = new Set();

        // 1. EXECUTE FILTER & RENDER FUNCTION
        function executeFilter() {
            const searchVal = document.getElementById('searchInput').value.toLowerCase().trim();
            const companyVal = document.getElementById('companyDropdown').value;
            const minSal = parseInt(document.getElementById('salaryDropdown').value);
            const sortBy = document.getElementById('sortSelect').value;

            let filtered = masterJobs.filter(job => {
                // Tier filter
                if (currentTier !== 'ALL' && job.tier !== currentTier) return false;

                // Company filter
                if (companyVal !== 'ALL' && job.company.toLowerCase() !== companyVal.toLowerCase()) return false;

                // Min salary filter
                if (job.salaryVal < minSal) return false;

                // Category tag filter
                if (currentTag) {
                    if (currentTag === 'Remote' && !job.isRemote) return false;
                    if (currentTag !== 'Remote' && job.category !== currentTag) return false;
                }

                // Keyword search filter
                if (searchVal) {
                    const matchText = `${job.title} ${job.company} ${job.overview} ${job.tags.join(' ')}`.toLowerCase();
                    if (!matchText.includes(searchVal)) return false;
                }

                return true;
            });

            // Sorting logic
            if (sortBy === 'SALARY_HIGH') {
                filtered.sort((a, b) => b.salaryVal - a.salaryVal);
            } else if (sortBy === 'MATCH') {
                filtered.sort((a, b) => b.matchScore - a.matchScore);
            } else if (sortBy === 'RATING') {
                filtered.sort((a, b) => b.rating - a.rating);
            }

            renderJobList(filtered);
        }

        // 2. RENDER JOB CARDS TO DOM
        function renderJobList(jobs) {
            const gridContainer = document.getElementById('jobsGrid');
            document.getElementById('resultsCountBadge').innerText = `${jobs.length} Positions Available`;

            if (jobs.length === 0) {
                gridContainer.innerHTML = `
                    <div class="col-span-full glass-panel p-12 text-center rounded-3xl space-y-4">
                        <i data-lucide="search-x" class="w-12 h-12 text-slate-500 mx-auto"></i>
                        <h4 class="text-xl font-bold text-white">No Matching Requisitions Found</h4>
                        <p class="text-xs text-slate-400 max-w-md mx-auto">Try broadening your search query, clearing tag filters, or selecting "All Tech Companies".</p>
                        <button onclick="resetFilters()" class="px-5 py-2.5 rounded-xl bg-cyber-cyan/10 text-cyber-cyan border border-cyber-cyan/30 text-xs font-mono font-bold hover:bg-cyber-cyan hover:text-black transition-all">
                            Reset Filters
                        </button>
                    </div>
                `;
                lucide.createIcons();
                return;
            }

            gridContainer.innerHTML = jobs.map(job => {
                const isSaved = savedJobIds.has(job.id);
                const isExpanded = expandedCardIds.has(job.id);

                return `
                    <div class="glass-panel glass-panel-hover p-6 rounded-3xl space-y-5 border-white/10 relative transition-all" id="card-${job.id}">
                        
                        <!-- Top Header Line -->
                        <div class="flex items-start justify-between gap-3">
                            <div class="flex items-center gap-3">
                                <div class="w-12 h-12 rounded-2xl bg-slate-950 border border-white/10 flex items-center justify-center font-black text-${job.companyColor} text-lg font-mono shrink-0">
                                    ${job.logoText}
                                </div>
                                <div>
                                    <div class="flex items-center gap-2">
                                        <span class="text-xs font-mono font-bold text-${job.companyColor}">${job.company}</span>
                                        <span class="text-[10px] font-mono px-2 py-0.5 rounded bg-white/5 text-slate-400 border border-white/5">${job.tier}</span>
                                    </div>
                                    <h3 class="text-base font-bold text-white group-hover:text-cyber-cyan transition-colors leading-snug">${job.title}</h3>
                                </div>
                            </div>
                            
                            <!-- Save / Bookmark Button -->
                            <button onclick="toggleSaveJob('${job.id}')" class="p-2 rounded-xl glass-panel hover:border-cyber-cyan text-slate-400 hover:text-cyber-cyan transition-all shrink-0">
                                <i data-lucide="bookmark" class="w-4 h-4 ${isSaved ? 'text-cyber-cyan fill-cyber-cyan' : ''}"></i>
                            </button>
                        </div>

                        <!-- Location & Rating -->
                        <div class="flex items-center justify-between text-xs font-mono text-slate-400">
                            <span class="flex items-center gap-1">
                                <i data-lucide="map-pin" class="w-3.5 h-3.5 text-cyber-cyan"></i>
                                ${job.location}
                            </span>
                            <span class="flex items-center gap-1 text-cyber-gold font-bold">
                                <i data-lucide="star" class="w-3.5 h-3.5 fill-cyber-gold"></i>
                                ${job.rating}
                            </span>
                        </div>

                        <p class="text-xs text-slate-300 leading-relaxed">${job.overview}</p>

                        <!-- Skill Tags -->
                        <div class="flex flex-wrap gap-1.5">
                            ${job.tags.map(t => `<span class="text-[10px] font-mono px-2.5 py-1 rounded-lg bg-slate-950 text-slate-300 border border-white/5">${t}</span>`).join('')}
                        </div>

                        <!-- Expanded Telemetry Drawer -->
                        ${isExpanded ? `
                            <div class="pt-4 mt-2 border-t border-white/10 space-y-3 text-xs font-mono animate-in fade-in duration-200">
                                <div class="p-3 rounded-2xl bg-slate-950/90 border border-white/5 space-y-2">
                                    <div class="text-cyber-cyan font-bold flex items-center gap-1.5">
                                        <i data-lucide="cpu" class="w-4 h-4"></i>
                                        <span>TEAM & COMPUTE SPECIFICATION</span>
                                    </div>
                                    <p class="text-slate-300"><strong class="text-white">Unit:</strong> ${job.details.team}</p>
                                    <p class="text-slate-300"><strong class="text-white">Compute Stack:</strong> ${job.details.compute}</p>
                                </div>

                                <div>
                                    <span class="text-slate-400 block mb-1 font-bold">REQUIRED SKILL STACK VECTORS:</span>
                                    <ul class="list-disc list-inside space-y-1 text-slate-300">
                                        ${job.details.requirements.map(req => `<li>${req}</li>`).join('')}
                                    </ul>
                                </div>
                            </div>
                        ` : ''}

                        <!-- Footer Action Bar -->
                        <div class="pt-4 border-t border-white/10 flex flex-col sm:flex-row sm:items-center justify-between gap-3">
                            <div>
                                <span class="text-[10px] font-mono text-slate-500 uppercase block">Compensation Range</span>
                                <span class="text-xs font-bold font-mono text-cyber-gold">${job.salaryDisplay}</span>
                            </div>

                            <div class="flex items-center gap-2">
                                <button onclick="toggleExpandCard('${job.id}')" class="px-3 py-2 rounded-xl glass-panel text-xs font-mono text-slate-300 hover:text-white transition-all">
                                    ${isExpanded ? 'Less Telemetry' : 'Full Details'}
                                </button>
                                <button onclick="openApplyModal('${job.id}')" class="px-4 py-2 rounded-xl bg-cyber-cyan/10 hover:bg-cyber-cyan text-cyber-cyan hover:text-black font-bold text-xs font-mono border border-cyber-cyan/30 transition-all shadow-glow-cyan">
                                    Apply Now
                                </button>
                            </div>
                        </div>

                    </div>
                `;
            }).join('');

            lucide.createIcons();
        }

        // 3. TIER FILTERING
        function filterByTier(tier) {
            currentTier = tier;
            document.querySelectorAll('.tier-pill-btn').forEach(btn => {
                btn.className = 'tier-pill-btn px-3 py-1.5 rounded-xl text-xs font-mono font-semibold transition-all text-slate-400 hover:text-white';
            });
            const activeBtn = document.getElementById(`tierBtn-${tier}`);
            if (activeBtn) {
                activeBtn.className = 'tier-pill-btn px-3 py-1.5 rounded-xl text-xs font-mono font-semibold transition-all bg-cyber-cyan/20 text-cyber-cyan border border-cyber-cyan/30';
            }
            executeFilter();
        }

        // 4. QUICK COMPANY FILTER
        function quickFilterCompany(companyName) {
            document.getElementById('companyDropdown').value = companyName;
            executeFilter();
            showToast(`Filtered for ${companyName} positions`);
        }

        // 5. TAG FILTERING
        function toggleTagFilter(tag) {
            if (currentTag === tag) {
                currentTag = null;
            } else {
                currentTag = tag;
            }

            document.querySelectorAll('.tag-btn').forEach(btn => {
                btn.classList.remove('bg-cyber-cyan', 'text-black', 'font-bold');
                btn.classList.add('bg-slate-900', 'text-slate-400');
            });

            if (currentTag) {
                const activeTagBtn = document.getElementById(`tag-${tag}`);
                if (activeTagBtn) {
                    activeTagBtn.classList.remove('bg-slate-900', 'text-slate-400');
                    activeTagBtn.classList.add('bg-cyber-cyan', 'text-black', 'font-bold');
                }
            }
            executeFilter();
        }

        // 6. BOOKMARK / SAVE JOB TOGGLE
        function toggleSaveJob(id) {
            if (savedJobIds.has(id)) {
                savedJobIds.delete(id);
                showToast("Job removed from saved list");
            } else {
                savedJobIds.add(id);
                showToast("Job saved to your bookmarks!");
            }
            document.getElementById('savedCountHeader').innerText = savedJobIds.size;
            executeFilter();
        }

        function toggleSavedFilter() {
            if (savedJobIds.size === 0) {
                showToast("No saved jobs yet! Click the bookmark icon on any job card.");
                return;
            }
            const savedJobs = masterJobs.filter(j => savedJobIds.has(j.id));
            renderJobList(savedJobs);
            showToast(`Showing ${savedJobs.length} saved roles`);
        }

        // 7. EXPAND/COLLAPSE CARD DETAILS
        function toggleExpandCard(id) {
            if (expandedCardIds.has(id)) {
                expandedCardIds.delete(id);
            } else {
                expandedCardIds.add(id);
            }
            executeFilter();
        }

        // 8. RESET ALL FILTERS
        function resetFilters() {
            currentTier = 'ALL';
            currentTag = null;
            document.getElementById('searchInput').value = '';
            document.getElementById('companyDropdown').value = 'ALL';
            document.getElementById('salaryDropdown').value = '0';
            document.getElementById('sortSelect').value = 'MATCH';
            filterByTier('ALL');
            showToast("All filter vectors reset");
        }

        // 9. APPLY MODAL LOGIC
        let activeApplyJob = null;

        function openApplyModal(jobId) {
            activeApplyJob = masterJobs.find(j => j.id === jobId) || masterJobs[0];
            document.getElementById('modalJobTitle').innerText = activeApplyJob.title;
            document.getElementById('modalCompanyInfo').innerText = `${activeApplyJob.company} • ${activeApplyJob.salaryDisplay}`;
            document.getElementById('applyModal').classList.remove('hidden');
        }

        function triggerQuickApplyAny() {
            openApplyModal(masterJobs[0].id);
        }

        function closeApplyModal() {
            document.getElementById('applyModal').classList.add('hidden');
        }

        function handleApplicationSubmit(e) {
            e.preventDefault();
            const candidateName = document.getElementById('applicantName').value;
            closeApplyModal();
            showToast(`🚀 Application vector for ${candidateName} submitted to ${activeApplyJob.company}!`);
        }

        // 10. AI MATCH CALCULATOR SIMULATOR
        function openMatchCalculatorModal() {
            document.getElementById('matchCalcModal').classList.remove('hidden');
            runSimCalc();
        }

        function closeMatchCalcModal() {
            document.getElementById('matchCalcModal').classList.add('hidden');
        }

        function runSimCalc() {
            const exp = parseInt(document.getElementById('expSliderSim').value);
            const stack = parseInt(document.getElementById('stackSliderSim').value);

            document.getElementById('expValSim').innerText = `${exp} Years`;
            document.getElementById('stackValSim').innerText = `${stack}%`;

            const scoreNv = Math.min(99.4, (exp * 2.5) + (stack * 0.85)).toFixed(1);
            const scoreGgl = Math.min(98.9, (exp * 2.3) + (stack * 0.82)).toFixed(1);
            const scoreStrp = Math.min(99.8, (exp * 2.7) + (stack * 0.88)).toFixed(1);

            document.getElementById('simNvMatch').innerText = `${scoreNv}%`;
            document.getElementById('simGglMatch').innerText = `${scoreGgl}%`;
            document.getElementById('simStripeMatch').innerText = `${scoreStrp}%`;
        }

        // 11. NOTIFICATION TOAST SYSTEM
        function showToast(message) {
            const container = document.getElementById('toastContainer');
            const toast = document.createElement('div');
            toast.className = 'glass-panel px-4 py-3 rounded-2xl text-xs font-mono text-white border-cyber-cyan/50 shadow-glow-cyan flex items-center gap-2 pointer-events-auto toast-enter';
            toast.innerHTML = `
                <i data-lucide="check-circle" class="w-4 h-4 text-cyber-cyan"></i>
                <span>${message}</span>
            `;
            container.appendChild(toast);
            lucide.createIcons();

            setTimeout(() => toast.classList.add('toast-active'), 10);
            setTimeout(() => {
                toast.classList.remove('toast-active');
                setTimeout(() => toast.remove(), 300);
            }, 3000);
        }

        // 12. THREE.JS INTERACTIVE NODE BACKGROUND
        function initWebGL() {
            const container = document.getElementById('webgl-bg');
            const scene = new THREE.Scene();
            scene.fog = new THREE.FogExp2(0x050811, 0.025);

            const camera = new THREE.PerspectiveCamera(60, window.innerWidth / window.innerHeight, 0.1, 1000);
            camera.position.z = 30;

            const renderer = new THREE.WebGLRenderer({ antialias: true, alpha: true });
            renderer.setSize(window.innerWidth, window.innerHeight);
            renderer.setPixelRatio(Math.min(window.devicePixelRatio, 2));
            container.appendChild(renderer.domElement);

            // Create Particle Grid Nodes
            const pCount = 140;
            const geometry = new THREE.BufferGeometry();
            const positions = new Float32Array(pCount * 3);

            for (let i = 0; i < pCount; i++) {
                positions[i * 3] = (Math.random() - 0.5) * 80;
                positions[i * 3 + 1] = (Math.random() - 0.5) * 80;
                positions[i * 3 + 2] = (Math.random() - 0.5) * 50;
            }

            geometry.setAttribute('position', new THREE.BufferAttribute(positions, 3));
            const material = new THREE.PointsMaterial({ color: 0x00f3ff, size: 0.6, transparent: true, opacity: 0.6 });
            const particles = new THREE.Points(geometry, material);
            scene.add(particles);

            // Mouse Interaction Parallax
            let mouseX = 0, mouseY = 0;
            document.addEventListener('mousemove', (e) => {
                mouseX = (e.clientX - window.innerWidth / 2) * 0.0008;
                mouseY = (e.clientY - window.innerHeight / 2) * 0.0008;
            });

            window.addEventListener('resize', () => {
                camera.aspect = window.innerWidth / window.innerHeight;
                camera.updateProjectionMatrix();
                renderer.setSize(window.innerWidth, window.innerHeight);
            });

            function animate() {
                requestAnimationFrame(animate);
                particles.rotation.y += 0.001;
                particles.rotation.x += 0.0005;
                camera.position.x += (mouseX * 10 - camera.position.x) * 0.05;
                camera.position.y += (-mouseY * 10 - camera.position.y) * 0.05;
                camera.lookAt(scene.position);
                renderer.render(scene, camera);
            }
            animate();
        }

        // INITIALIZE APPLICATION
        window.onload = function() {
            lucide.createIcons();
            executeFilter();
            initWebGL();
        };
    </script>
</body>
</html>
