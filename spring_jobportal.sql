-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Mar 25, 2026 at 08:56 AM
-- Server version: 10.4.32-MariaDB
-- PHP Version: 8.0.30

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `spring_jobportal`
--

-- --------------------------------------------------------

--
-- Table structure for table `chatroom`
--

CREATE TABLE `chatroom` (
  `id` int(11) NOT NULL,
  `user` int(11) NOT NULL,
  `status` int(11) NOT NULL,
  `updatedat` timestamp NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `chatroom`
--

INSERT INTO `chatroom` (`id`, `user`, `status`, `updatedat`) VALUES
(1, 3, 0, '2025-02-04 12:44:46'),
(2, 2, 1, '2025-03-02 06:05:42'),
(3, 5, 1, '2025-03-02 05:57:09'),
(4, 1, 0, '2026-03-25 07:11:50');

-- --------------------------------------------------------

--
-- Table structure for table `companies`
--

CREATE TABLE `companies` (
  `id` bigint(20) UNSIGNED NOT NULL,
  `user_id` bigint(20) UNSIGNED NOT NULL,
  `company_category_id` int(10) UNSIGNED NOT NULL,
  `logo` varchar(255) NOT NULL,
  `title` varchar(50) NOT NULL,
  `description` text NOT NULL,
  `website` varchar(255) NOT NULL,
  `cover_img` varchar(255) NOT NULL,
  `created_at` timestamp NULL DEFAULT NULL,
  `updated_at` timestamp NULL DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Dumping data for table `companies`
--

INSERT INTO `companies` (`id`, `user_id`, `company_category_id`, `logo`, `title`, `description`, `website`, `cover_img`, `created_at`, `updated_at`) VALUES
(1, 2, 1, 'images/logo/7.png', 'Gabrato company', 'This company Pvt Ltd is the company specialized to help organizations with financial technology solutions. We provide solutions such comprehensive mobile and online payment solutions and gateway facilitating services. We facilitate in online transaction settlement service to merchants and their banks to be able to accept/acquire payments from third party payment sources. We provide technology and solutions for acquiring payment from 3rd party wallets, smart wallets solutions, merchant management solutions and host of other solutions..', 'https://www.companywebsite.com', 'nocover', '2025-02-01 03:35:06', '2025-02-01 03:35:06');

-- --------------------------------------------------------

--
-- Table structure for table `company_categories`
--

CREATE TABLE `company_categories` (
  `id` bigint(20) UNSIGNED NOT NULL,
  `name` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Dumping data for table `company_categories`
--

INSERT INTO `company_categories` (`id`, `name`) VALUES
(2, 'Marketing / Advertising'),
(3, 'General Mgmt'),
(4, 'Banking / Insurance /Financial Services'),
(5, 'Construction / Engineering / Architects '),
(6, 'Creative / Graphics / Designing'),
(7, 'Social work'),
(8, 'hospitality'),
(9, 'journalism-editor-media'),
(10, 'Agriculture + Livestock'),
(11, 'Teaching profession'),
(12, 'Engineer'),
(13, 'Sales'),
(14, 'Leadership'),
(15, 'Web development'),
(16, 'Mobile App'),
(17, 'Sales'),
(18, 'E-Commerce'),
(19, 'Others'),
(21, 'kdjfk');

-- --------------------------------------------------------

--
-- Table structure for table `failed_jobs`
--

CREATE TABLE `failed_jobs` (
  `id` bigint(20) UNSIGNED NOT NULL,
  `uuid` varchar(255) NOT NULL,
  `connection` text NOT NULL,
  `queue` text NOT NULL,
  `payload` longtext NOT NULL,
  `exception` longtext NOT NULL,
  `failed_at` timestamp NOT NULL DEFAULT current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- --------------------------------------------------------

--
-- Table structure for table `job_applications`
--

CREATE TABLE `job_applications` (
  `id` bigint(20) UNSIGNED NOT NULL,
  `user` bigint(20) UNSIGNED NOT NULL,
  `post` bigint(20) UNSIGNED NOT NULL,
  `status` int(11) NOT NULL,
  `createdat` timestamp NULL DEFAULT current_timestamp(),
  `updatedat` timestamp NULL DEFAULT NULL ON UPDATE current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Dumping data for table `job_applications`
--

INSERT INTO `job_applications` (`id`, `user`, `post`, `status`, `createdat`, `updatedat`) VALUES
(1, 3, 7, 0, '2025-02-03 07:01:03', NULL),
(2, 3, 5, 1, '2025-02-03 07:14:27', NULL),
(3, 3, 6, 0, '2025-02-04 06:22:52', NULL),
(4, 6, 8, 1, '2026-03-25 07:52:19', NULL);

-- --------------------------------------------------------

--
-- Table structure for table `job_categories`
--

CREATE TABLE `job_categories` (
  `id` bigint(20) UNSIGNED NOT NULL,
  `name` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Dumping data for table `job_categories`
--

INSERT INTO `job_categories` (`id`, `name`) VALUES
(1, 'IT & Telecommunication'),
(2, 'Marketing / Advertising'),
(3, 'General Mgmt'),
(4, 'Banking / Insurance /Financial Services'),
(5, 'Construction / Engineering / Architects '),
(6, 'Creative / Graphics / Designing'),
(7, 'Social work'),
(8, 'hospitality'),
(9, 'journalism-editor-media'),
(10, 'Agriculture + Livestock'),
(11, 'Teaching profession'),
(12, 'Engineer'),
(13, 'Sales'),
(14, 'Leadership'),
(15, 'Web development'),
(16, 'Mobile App'),
(17, 'Sales'),
(18, 'E-Commerce'),
(19, 'Others');

-- --------------------------------------------------------

--
-- Table structure for table `posts`
--

CREATE TABLE `posts` (
  `id` bigint(20) UNSIGNED NOT NULL,
  `user` bigint(20) UNSIGNED NOT NULL,
  `jobtitle` varchar(50) NOT NULL,
  `category` int(11) NOT NULL,
  `joblevel` varchar(20) NOT NULL,
  `vacancycount` smallint(5) UNSIGNED NOT NULL,
  `employmenttype` varchar(255) NOT NULL,
  `salary` varchar(30) NOT NULL,
  `joblocation` varchar(255) NOT NULL,
  `deadline` timestamp NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp(),
  `educationlevel` varchar(255) NOT NULL,
  `experience` varchar(255) NOT NULL,
  `skills` varchar(255) NOT NULL,
  `specifications` text NOT NULL,
  `views` mediumint(8) UNSIGNED NOT NULL DEFAULT 1,
  `status` int(11) NOT NULL,
  `createdat` timestamp NULL DEFAULT NULL,
  `updatedat` timestamp NULL DEFAULT current_timestamp() ON UPDATE current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Dumping data for table `posts`
--

INSERT INTO `posts` (`id`, `user`, `jobtitle`, `category`, `joblevel`, `vacancycount`, `employmenttype`, `salary`, `joblocation`, `deadline`, `educationlevel`, `experience`, `skills`, `specifications`, `views`, `status`, `createdat`, `updatedat`) VALUES
(1, 2, 'Php laravel developer', 1, 'Senior level', 2, 'full time', '20k - 50k', 'kathmandu-18,Nepal', '2025-02-03 07:12:24', 'bachelors', '2 years', 'Team player, Active listener', '<p></p>', 9, 1, '2025-02-01 03:35:06', '2025-02-03 07:12:24'),
(2, 2, 'Marketing Expert', 1, 'Senior level', 2, 'full time', '20k - 50k', 'kathmandu-18,Nepal', '2025-02-02 21:28:07', 'bachelors', '2 years', 'Team player, Active listener', '<p></p>', 5, 1, '2025-02-01 03:35:06', '2025-02-02 21:28:07'),
(3, 2, 'Professional designer', 1, 'Top level', 10, 'Part time', '20k - 50k', 'kathmandu-18,Nepal', '2025-02-03 07:12:24', 'bachelors', '2 years', 'Team player, Active listener', '<p></p>', 2, 1, '2025-02-01 03:35:06', '2025-02-03 07:12:24'),
(4, 2, 'Dotnet programmer', 1, 'Senior level', 8, 'full time', '20k - 50k', 'kathmandu-18,Nepal', '2025-02-02 21:28:14', 'high school', '2 years', 'Team player, Active listener', '<p></p>', 1, 0, '2025-02-01 03:35:06', '2025-02-02 21:28:14'),
(5, 2, 'Sales Executive', 1, 'Senior level', 4, 'Part time', '20k - 50k', 'kathmandu-18,Nepal', '2025-02-02 21:28:17', 'bachelors', '2 years', 'Team player, Active listener', '<p></p>', 6, 1, '2025-02-01 03:35:06', '2025-02-02 21:28:17'),
(6, 2, 'Maths Teacher', 1, 'Senior level', 6, 'full time', '20k - 50k', 'kathmandu-18,Nepal', '2025-02-03 07:12:24', 'master', '2 years', 'Team player, Active listener', '<p></p>', 5, 1, '2025-02-01 03:35:06', '2025-02-03 07:12:24'),
(7, 2, 'test', 1, 'Entry level', 2, 'Internship', '20k - 30k', 'test', '2025-02-04 06:46:20', 'Master', '1 year', 'test', 'test', 44, 1, '2025-02-03 06:46:12', '2025-02-04 06:46:20'),
(8, 2, 'Software Testing', 1, 'Entry level', 3, 'Full Time', '20k', 'mysore', '2026-03-24 18:30:00', 'Bachelors', '1 year', 'java, spring boot', 'we need a software testing candidates', 5, 1, '2026-03-25 07:24:15', NULL);

-- --------------------------------------------------------

--
-- Table structure for table `saved_posts`
--

CREATE TABLE `saved_posts` (
  `id` int(11) NOT NULL,
  `user` int(11) NOT NULL,
  `post` int(11) NOT NULL,
  `createdat` timestamp NOT NULL DEFAULT current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `saved_posts`
--

INSERT INTO `saved_posts` (`id`, `user`, `post`, `createdat`) VALUES
(1, 3, 7, '2025-02-03 07:08:28'),
(2, 3, 5, '2025-02-03 07:14:30'),
(3, 3, 6, '2025-02-04 06:22:56');

-- --------------------------------------------------------

--
-- Table structure for table `users`
--

CREATE TABLE `users` (
  `id` bigint(20) UNSIGNED NOT NULL,
  `type` varchar(100) NOT NULL,
  `name` varchar(255) NOT NULL,
  `email` varchar(255) NOT NULL,
  `mobile` varchar(20) NOT NULL,
  `password` varchar(255) NOT NULL,
  `status` int(11) NOT NULL,
  `createdat` timestamp NULL DEFAULT current_timestamp(),
  `updatedat` timestamp NULL DEFAULT NULL ON UPDATE current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Dumping data for table `users`
--

INSERT INTO `users` (`id`, `type`, `name`, `email`, `mobile`, `password`, `status`, `createdat`, `updatedat`) VALUES
(1, 'ADMIN', 'admin user', 'admin@admin.com', '8553305224', '$2a$10$4poIAB7zrnzdVr9MhrEHLOVNhSjMRYr8iixPOaonJc/HxjTiqs1EO', 1, '2025-02-01 03:35:06', '2025-02-02 01:59:56'),
(2, 'EMPLOYER', 'author user', 'author@author.com', '8553305223', '$2a$10$K5ocw5mee9iZvphJgZ6sHOEJQLAf/BkfNa.TxPN0Fau2.o0eaSm3m', 1, '2025-02-01 03:35:06', '2025-03-02 05:59:47'),
(3, 'JOBSEEKER', 'simple user', 'user@user.com', '8553305222', '$2a$10$4poIAB7zrnzdVr9MhrEHLOVNhSjMRYr8iixPOaonJc/HxjTiqs1EO', 1, '2025-02-01 03:35:06', '2025-02-02 06:49:00'),
(4, 'EMPLOYER', 'XYZ company', 'xyz@gmail.com', '8553305227', '$2a$10$oAeXdOFNVYnsYziKfv0ePejMuKlhbjjyYLXC7yv91v.xwXVLjFlCC', 1, '2025-02-04 03:19:16', NULL),
(5, 'JOBSEEKER', 'shyam', 'test1@gmail.com', '8553305226', '$2a$10$K5ocw5mee9iZvphJgZ6sHOEJQLAf/BkfNa.TxPN0Fau2.o0eaSm3m', 1, '2025-02-04 06:08:02', NULL),
(6, 'JOBSEEKER', 'prajwal', 'prajwal@gmail.com', '9898787678', '$2a$10$Yvp7N7du1niKH903LLytZ.FMGABUDMRlqqbkZkab/jJR2tWtqKVZC', 1, '2026-03-25 07:25:59', NULL);

-- --------------------------------------------------------

--
-- Table structure for table `user_company`
--

CREATE TABLE `user_company` (
  `id` int(11) NOT NULL,
  `user` int(11) NOT NULL,
  `category` int(11) NOT NULL,
  `url` varchar(500) NOT NULL,
  `cdesc` text NOT NULL,
  `updatedat` timestamp NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `user_company`
--

INSERT INTO `user_company` (`id`, `user`, `category`, `url`, `cdesc`, `updatedat`) VALUES
(1, 2, 8, 'https://google.com', 'test', '2025-02-02 13:38:16');

-- --------------------------------------------------------

--
-- Table structure for table `user_educations`
--

CREATE TABLE `user_educations` (
  `id` int(11) NOT NULL,
  `user` int(11) NOT NULL,
  `institution` varchar(500) NOT NULL,
  `course` varchar(500) NOT NULL,
  `dtfrom` varchar(100) NOT NULL,
  `dtto` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `user_educations`
--

INSERT INTO `user_educations` (`id`, `user`, `institution`, `course`, `dtfrom`, `dtto`) VALUES
(16, 3, 'International University', 'Masters in Information Technology', '2011-06-01', '2013-05-31'),
(17, 3, ' Regional College', 'Bachelor of Computer Science', '2007-06-01', '2011-05-31'),
(18, 3, 'Mt. High Scool', 'Science and Mathematics', '1995-06-01', '2007-05-31'),
(19, 6, 'NIE', 'CS', '2024-03-09', '2025-03-05');

-- --------------------------------------------------------

--
-- Table structure for table `user_experiences`
--

CREATE TABLE `user_experiences` (
  `id` int(11) NOT NULL,
  `user` int(11) NOT NULL,
  `company` varchar(500) NOT NULL,
  `position` varchar(500) NOT NULL,
  `dtfrom` varchar(100) NOT NULL,
  `dtto` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `user_experiences`
--

INSERT INTO `user_experiences` (`id`, `user`, `company`, `position`, `dtfrom`, `dtto`) VALUES
(7, 3, 'Creative Agency', 'Frontend Developer', '2013-06-01', ''),
(8, 6, 'TATA', 'Software Tester', '2025-03-04', '2026-03-07');

-- --------------------------------------------------------

--
-- Table structure for table `user_jobseeker`
--

CREATE TABLE `user_jobseeker` (
  `id` int(11) NOT NULL,
  `user` int(11) NOT NULL,
  `subtitle` varchar(100) NOT NULL,
  `aboutme` text NOT NULL,
  `skills` text NOT NULL,
  `experience` text DEFAULT NULL,
  `education` text DEFAULT NULL,
  `address` text NOT NULL,
  `createdat` timestamp NOT NULL DEFAULT current_timestamp(),
  `updatedat` timestamp NULL DEFAULT NULL ON UPDATE current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `user_jobseeker`
--

INSERT INTO `user_jobseeker` (`id`, `user`, `subtitle`, `aboutme`, `skills`, `experience`, `education`, `address`, `createdat`, `updatedat`) VALUES
(1, 5, 'Graphic Designer & Web Developer', 'Hello! I’m Joyce Harrison. I am passionate about UI/UX design and Web Design. I am a skilled Front-end Developer and master of Graphic Design tools such as Photoshop and Sketch.', 'HTML,CSS,JavaScript,Adobe Photoshop,Sketch,Adobe XD', '[]', '[com.project.jobportal.Education@783ffa82]', '140, City Center, New York, U.S.A', '2025-02-03 09:25:52', '2025-03-02 05:55:29'),
(2, 6, 'tester', 'tester', 'java, spring boot', NULL, NULL, 'mysore', '2026-03-25 07:51:53', NULL);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `chatroom`
--
ALTER TABLE `chatroom`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `user` (`user`);

--
-- Indexes for table `companies`
--
ALTER TABLE `companies`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `company_categories`
--
ALTER TABLE `company_categories`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `failed_jobs`
--
ALTER TABLE `failed_jobs`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `failed_jobs_uuid_unique` (`uuid`);

--
-- Indexes for table `job_applications`
--
ALTER TABLE `job_applications`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `job_categories`
--
ALTER TABLE `job_categories`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `posts`
--
ALTER TABLE `posts`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `saved_posts`
--
ALTER TABLE `saved_posts`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `users`
--
ALTER TABLE `users`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `users_email_unique` (`email`);

--
-- Indexes for table `user_company`
--
ALTER TABLE `user_company`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `user_educations`
--
ALTER TABLE `user_educations`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `user_experiences`
--
ALTER TABLE `user_experiences`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `user_jobseeker`
--
ALTER TABLE `user_jobseeker`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `chatroom`
--
ALTER TABLE `chatroom`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT for table `companies`
--
ALTER TABLE `companies`
  MODIFY `id` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT for table `company_categories`
--
ALTER TABLE `company_categories`
  MODIFY `id` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=22;

--
-- AUTO_INCREMENT for table `failed_jobs`
--
ALTER TABLE `failed_jobs`
  MODIFY `id` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `job_applications`
--
ALTER TABLE `job_applications`
  MODIFY `id` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT for table `job_categories`
--
ALTER TABLE `job_categories`
  MODIFY `id` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=20;

--
-- AUTO_INCREMENT for table `posts`
--
ALTER TABLE `posts`
  MODIFY `id` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;

--
-- AUTO_INCREMENT for table `saved_posts`
--
ALTER TABLE `saved_posts`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT for table `users`
--
ALTER TABLE `users`
  MODIFY `id` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- AUTO_INCREMENT for table `user_company`
--
ALTER TABLE `user_company`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT for table `user_educations`
--
ALTER TABLE `user_educations`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=20;

--
-- AUTO_INCREMENT for table `user_experiences`
--
ALTER TABLE `user_experiences`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;

--
-- AUTO_INCREMENT for table `user_jobseeker`
--
ALTER TABLE `user_jobseeker`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
