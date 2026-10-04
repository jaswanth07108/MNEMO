package com.mnemo.app.data

import com.mnemo.app.domain.model.LocationConfidence
import com.mnemo.app.domain.model.Memory
import com.mnemo.app.domain.model.MemoryCapsule
import com.mnemo.app.domain.model.MemoryMetadata
import com.mnemo.app.domain.model.MemoryType
import com.mnemo.app.domain.model.PrivacyLevel

object DemoData {

    val INITIAL_MEMORIES = listOf(
        Memory(
            id = "demo-mem-1",
            title = "ASUS TUF Gaming F15 - RTX 4060",
            content = "ASUS TUF Gaming F15 Intel Core i7 13th Gen (16 GB DDR5 / 512 GB SSD / NVIDIA GeForce RTX 4060 / 140W TGP) FX507VV. ₹89,999.",
            type = MemoryType.PRODUCT_RESEARCH,
            privacyLevel = PrivacyLevel.NORMAL,
            createdAt = "2026-09-24T14:30:00Z",
            capturedAt = "2026-09-24T14:30:00Z",
            formattedDate = "Sep 24, 2026",
            sourceType = "📸 Screenshot — Sep 24",
            extractedText = "ASUS TUF GAMING F15\nProcessor: Intel i7-13620H\nRAM: 16 GB DDR5\nGraphics: NVIDIA GeForce RTX 4060 (140W TGP)\nPrice: ₹89,999",
            metadata = MemoryMetadata(
                product = "ASUS TUF Gaming F15",
                gpu = "NVIDIA GeForce RTX 4060",
                ram = "16 GB DDR5",
                price = "₹89,999",
                priceValue = 89999.0
            ),
            entities = listOf("ASUS TUF", "RTX 4060", "16GB RAM", "140W TGP"),
            topics = listOf("Gaming Laptop Research"),
            tags = listOf("laptop", "gaming", "rtx 4060", "asus", "hardware"),
            isDemoData = true
        ),
        Memory(
            id = "demo-mem-2",
            title = "Lenovo LOQ 15 - Intel i5 RTX 4060",
            content = "Lenovo LOQ Intel Core i5 13th Gen 13450HX (16 GB / 512 GB SSD / NVIDIA GeForce RTX 4060 115W TGP). ₹87,490.",
            type = MemoryType.PRODUCT_RESEARCH,
            privacyLevel = PrivacyLevel.NORMAL,
            createdAt = "2026-09-22T11:20:00Z",
            capturedAt = "2026-09-22T11:20:00Z",
            formattedDate = "Sep 22, 2026",
            sourceType = "📸 Screenshot — Sep 22",
            extractedText = "LENOVO LOQ 15IRH8\nProcessor: Intel i5-13450HX\nRAM: 16 GB DDR5\nGraphics: NVIDIA GeForce RTX 4060 (115W TGP)\nPrice: ₹87,490",
            metadata = MemoryMetadata(
                product = "Lenovo LOQ 15",
                gpu = "NVIDIA GeForce RTX 4060",
                ram = "16 GB DDR5",
                price = "₹87,490",
                priceValue = 87490.0
            ),
            entities = listOf("Lenovo LOQ", "RTX 4060", "16GB RAM"),
            topics = listOf("Gaming Laptop Research"),
            tags = listOf("laptop", "gaming", "lenovo", "rtx 4060"),
            isDemoData = true
        ),
        Memory(
            id = "demo-mem-3",
            title = "Acer Nitro V 15 - Ryzen 7 RTX 4060",
            content = "Acer Nitro V 15 AMD Ryzen 7 7735HS (16 GB / 512 GB SSD / NVIDIA GeForce RTX 4060 75W TGP). ₹90,999.",
            type = MemoryType.PRODUCT_RESEARCH,
            privacyLevel = PrivacyLevel.NORMAL,
            createdAt = "2026-09-20T16:15:00Z",
            capturedAt = "2026-09-20T16:15:00Z",
            formattedDate = "Sep 20, 2026",
            sourceType = "📸 Screenshot — Sep 20",
            extractedText = "ACER NITRO V 15\nProcessor: AMD Ryzen 7 7735HS\nRAM: 16 GB DDR5\nGraphics: NVIDIA GeForce RTX 4060\nPrice: ₹90,999",
            metadata = MemoryMetadata(
                product = "Acer Nitro V 15",
                gpu = "NVIDIA GeForce RTX 4060",
                ram = "16 GB DDR5",
                price = "₹90,999",
                priceValue = 90999.0
            ),
            entities = listOf("Acer Nitro", "RTX 4060", "Ryzen 7"),
            topics = listOf("Gaming Laptop Research"),
            tags = listOf("laptop", "gaming", "acer", "rtx 4060"),
            isDemoData = true
        ),
        Memory(
            id = "demo-mem-4",
            title = "HP Victus 16 - Ryzen 5 RTX 4050",
            content = "HP Victus 16 AMD Ryzen 5 7640HS (16 GB DDR5 / 512 GB SSD / RTX 4050 120W TGP). ₹88,990.",
            type = MemoryType.PRODUCT_RESEARCH,
            privacyLevel = PrivacyLevel.NORMAL,
            createdAt = "2026-09-18T09:40:00Z",
            capturedAt = "2026-09-18T09:40:00Z",
            formattedDate = "Sep 18, 2026",
            sourceType = "📸 Screenshot — Sep 18",
            extractedText = "HP VICTUS 16\nProcessor: AMD Ryzen 5 7640HS\nRAM: 16 GB DDR5\nGraphics: NVIDIA GeForce RTX 4050\nPrice: ₹88,990",
            metadata = MemoryMetadata(
                product = "HP Victus 16",
                gpu = "NVIDIA GeForce RTX 4050",
                ram = "16 GB DDR5",
                price = "₹88,990",
                priceValue = 88990.0
            ),
            entities = listOf("HP Victus", "RTX 4050", "16GB RAM"),
            topics = listOf("Gaming Laptop Research"),
            tags = listOf("laptop", "gaming", "hp", "rtx 4050"),
            isDemoData = true
        ),
        Memory(
            id = "demo-mem-5",
            title = "🎓 B.Tech Degree Certificate (SAMPLE/DEMO)",
            content = "Official Bachelor of Technology (Computer Science & Engineering) Degree Certificate. Awarded First Class with Distinction. Year: 2025.",
            type = MemoryType.DOCUMENT,
            privacyLevel = PrivacyLevel.PERSONAL,
            createdAt = "2025-06-15T10:00:00Z",
            capturedAt = "2025-06-15T10:00:00Z",
            formattedDate = "Jun 15, 2025",
            sourceType = "📄 PDF — Jun 15, 2025",
            extractedText = "BACHELOR OF TECHNOLOGY\nComputer Science & Engineering\nFirst Class with Distinction\nGraduation Year: 2025\n[SAMPLE / DEMO DOCUMENT]",
            metadata = MemoryMetadata(
                documentCategory = "Degree Certificate",
                year = 2025,
                institution = "JNTU Hyderabad"
            ),
            entities = listOf("B.Tech Degree", "Computer Science", "First Class", "2025"),
            topics = listOf("B.Tech Journey", "Education"),
            tags = listOf("btech", "degree", "certificate", "education", "college"),
            isDemoData = true
        ),
        Memory(
            id = "demo-mem-6",
            title = "📜 10th Class SSC Marks Memo (SAMPLE/DEMO)",
            content = "Secondary School Certificate (10th Standard) Marks Memorandum. Grade Points Average: 9.8 / 10. Percentage: 94.2%. Year: 2019.",
            type = MemoryType.DOCUMENT,
            privacyLevel = PrivacyLevel.PERSONAL,
            createdAt = "2019-05-10T10:00:00Z",
            capturedAt = "2019-05-10T10:00:00Z",
            formattedDate = "May 10, 2019",
            sourceType = "📄 PDF — May 10, 2019",
            extractedText = "BOARD OF SECONDARY EDUCATION\nSSC Marks Memorandum\nGPA: 9.8 / 10.0\nPercentage: 94.2%\n[SAMPLE / DEMO DOCUMENT]",
            metadata = MemoryMetadata(
                documentCategory = "10th Marks Memo",
                year = 2019,
                percentage = "94.2%"
            ),
            entities = listOf("10th Class", "SSC Marks Memo", "GPA 9.8", "94.2%"),
            topics = listOf("Education"),
            tags = listOf("10th", "ssc", "marks memo", "education"),
            isDemoData = true
        ),
        Memory(
            id = "demo-mem-7",
            title = "📜 Intermediate Marks Certificate (SAMPLE/DEMO)",
            content = "Board of Intermediate Education Certificate (MPC Stream). Total Marks: 974 / 1000. Year: 2021.",
            type = MemoryType.DOCUMENT,
            privacyLevel = PrivacyLevel.PERSONAL,
            createdAt = "2021-05-20T10:00:00Z",
            capturedAt = "2021-05-20T10:00:00Z",
            formattedDate = "May 20, 2021",
            sourceType = "📄 PDF — May 20, 2021",
            extractedText = "BOARD OF INTERMEDIATE EDUCATION\nMPC Stream (Maths, Physics, Chemistry)\nTotal: 974 / 1000\n[SAMPLE / DEMO DOCUMENT]",
            metadata = MemoryMetadata(
                documentCategory = "Intermediate Certificate",
                year = 2021,
                percentage = "97.4%"
            ),
            entities = listOf("Intermediate", "MPC", "974 Marks"),
            topics = listOf("Education"),
            tags = listOf("intermediate", "mpc", "marks memo", "education"),
            isDemoData = true
        ),
        Memory(
            id = "demo-mem-8",
            title = "📜 Python & AI Internship Certificate (SAMPLE/DEMO)",
            content = "Certificate of Completion: Python Data Science & AI Systems Internship. Successfully developed ML prediction pipelines. Year: 2025.",
            type = MemoryType.DOCUMENT,
            privacyLevel = PrivacyLevel.PERSONAL,
            createdAt = "2025-04-10T10:00:00Z",
            capturedAt = "2025-04-10T10:00:00Z",
            formattedDate = "Apr 10, 2025",
            sourceType = "📄 PDF — Apr 10, 2025",
            extractedText = "CERTIFICATE OF INTERNSHIP\nPython Data Science & AI Systems\nIssued to Student Candidate\n[SAMPLE / DEMO DOCUMENT]",
            metadata = MemoryMetadata(
                documentCategory = "Internship Certificate",
                year = 2025,
                institution = "AI Tech Solutions"
            ),
            entities = listOf("Python Certificate", "AI Internship", "2025"),
            topics = listOf("AI Projects", "Education"),
            tags = listOf("python", "internship", "certificate", "ai"),
            isDemoData = true
        ),
        Memory(
            id = "demo-mem-9",
            title = "🏖️ Beach Trip with Friends at Puducherry",
            content = "Unforgettable sunset at Paradise Beach, Puducherry with college friends. Great seafood, beach volleyball, and memories.",
            type = MemoryType.PHOTO,
            privacyLevel = PrivacyLevel.NORMAL,
            createdAt = "2025-08-14T17:30:00Z",
            capturedAt = "2025-08-14T17:30:00Z",
            formattedDate = "Aug 14, 2025",
            sourceType = "📷 Camera Photo — Aug 14, 2025",
            location = LocationConfidence(
                locationName = "Paradise Beach, Puducherry",
                isHighConfidence = true,
                latitude = 11.9142,
                longitude = 79.8155,
                evidenceList = listOf("EXIF GPS Metadata verified", "Date/Time matches hotel travel note", "Landmark visual clues")
            ),
            entities = listOf("Paradise Beach", "Puducherry", "College Friends", "Sunset"),
            topics = listOf("Puducherry Trip"),
            tags = listOf("beach", "puducherry", "friends", "trip", "vacation"),
            isDemoData = true
        ),
        Memory(
            id = "demo-mem-10",
            title = "🌦️ Smart Weather Station Final Year Project",
            content = "Final year major project documentation: ESP32 IoT sensor node reading temperature, humidity, pressure, streaming to Python ML prediction backend.",
            type = MemoryType.NOTE,
            privacyLevel = PrivacyLevel.NORMAL,
            createdAt = "2025-03-28T14:00:00Z",
            capturedAt = "2025-03-28T14:00:00Z",
            formattedDate = "Mar 28, 2025",
            sourceType = "✍ Note — Mar 28, 2025",
            extractedText = "IoT Weather Station Architecture:\n- ESP32 + BME280 sensor node\n- MQTT protocol to local server\n- Python Random Forest precipitation model",
            entities = listOf("IoT Weather Station", "ESP32", "Python ML", "Final Year Project"),
            topics = listOf("Weather Project", "Final Year"),
            tags = listOf("weather", "iot", "python", "final year", "project"),
            isDemoData = true
        ),
        Memory(
            id = "demo-mem-11",
            title = "🔴 Demo Financial Document (SAMPLE/DEMO)",
            content = "Sample Bank Account Confirmation & Tax ID Memo. Account: XXXX-1234. PAN: ABCDE1234F. [SAMPLE SYNTHETIC DEMO DATA]",
            type = MemoryType.DOCUMENT,
            privacyLevel = PrivacyLevel.HIGHLY_SENSITIVE,
            createdAt = "2026-09-01T10:00:00Z",
            capturedAt = "2026-09-01T10:00:00Z",
            formattedDate = "Sep 01, 2026",
            sourceType = "🔐 Vault Protected Document",
            extractedText = "SAMPLE BANK CONFIRMATION\nPAN: ABCDE1234F\nAccount: 987654321012\n[SYNTHETIC HACKATHON SAMPLE DATA - NEVER USE REAL REPOSITORIES]",
            metadata = MemoryMetadata(
                detectedSensitiveFields = listOf("PAN Number", "Bank Account Number")
            ),
            entities = listOf("Bank Account", "PAN Memo", "Sensitive Vault"),
            topics = listOf("Financial Vault"),
            tags = listOf("bank", "pan", "sensitive", "vault"),
            isDemoData = true
        )
    )

    val CAPSULES = listOf(
        MemoryCapsule(
            id = "capsule-btech",
            title = "🎓 B.Tech Journey",
            iconEmoji = "🎓",
            description = "4 years of engineering, exams, certificates, projects, and lifelong memories.",
            memoryIds = listOf("demo-mem-5", "demo-mem-6", "demo-mem-7", "demo-mem-8", "demo-mem-10"),
            documentCount = 4,
            photoCount = 10,
            noteCount = 6,
            voiceCount = 3,
            timeline = listOf("2022 (First Year)", "2023 (Second Year)", "2024 (Third Year)", "2025 (Graduation)")
        ),
        MemoryCapsule(
            id = "capsule-laptop",
            title = "💻 Gaming Laptop Research",
            iconEmoji = "💻",
            description = "Hardware specs, RTX 4060 comparisons, prices around ₹90,000, and TGP thermals.",
            memoryIds = listOf("demo-mem-1", "demo-mem-2", "demo-mem-3", "demo-mem-4"),
            documentCount = 1,
            photoCount = 4,
            noteCount = 2,
            voiceCount = 1,
            timeline = listOf("Sep 18", "Sep 20", "Sep 22", "Sep 24")
        ),
        MemoryCapsule(
            id = "capsule-trip",
            title = "🏖️ Puducherry Trip",
            iconEmoji = "🏖️",
            description = "Beach photos, French Quarter walks, seafood dinners with college friends.",
            memoryIds = listOf("demo-mem-9"),
            documentCount = 0,
            photoCount = 15,
            noteCount = 2,
            voiceCount = 1,
            timeline = listOf("Aug 14, 2025", "Aug 15, 2025", "Aug 16, 2025")
        ),
        MemoryCapsule(
            id = "capsule-weather",
            title = "🌦️ Weather Project",
            iconEmoji = "🌦️",
            description = "IoT ESP32 sensor node design, Python ML models, and final year report.",
            memoryIds = listOf("demo-mem-10"),
            documentCount = 3,
            photoCount = 5,
            noteCount = 5,
            voiceCount = 2,
            timeline = listOf("Jan 2025", "Feb 2025", "Mar 2025")
        )
    )
}
