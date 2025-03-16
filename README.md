# 🗳️ Voter Roll Downloader Automation Assignment

## 📌 Assignment Overview
This Assignment automates the process of downloading voter rolls from a government website using **Selenium WebDriver** with Java. It handles browser interactions, uses **Tesseract OCR** to extract text from images for verification, and downloads voter roll PDFs.

## 📂 Project Structure
| Folder/File         | Description                                                        |
|---------------------|--------------------------------------------------------------------|
| `/src/test/java`    | Core automation code: page objects, utilities, reusable libraries  |
| `/resources`        | Property files                                                     |
| `pom.xml`           | Maven file with required dependencies (already configured)         |
| `testng.xml`        | Test suite configuration file                                      |
| `README.md`         | Project documentation                                              |

## ⚙️ Technologies Used
- Java **11.0.25 (2024-10-15)**
- Selenium WebDriver
- TestNG
- Maven
- Tesseract OCR (for image text extraction)
- TestNG Reports & Screenshots

## 🔍 Tesseract OCR Setup

To extract text from images for verification code, **Tesseract OCR** is used.

### Installation Instructions:
1. **Download and Install Eclipse IDE Latest version**
2. - Official download wbsite: https://www.eclipse.org/downloads/packages/
1. **Download and Install Tesseract**
   - Official download: https://github.com/nguyenq/tess4j/releases
   - Download jar File
2. **Set Environment Variable**
   - Add the Tesseract Environment variable to your system.
   - **Variable Name:**  TESSDATA_PREFIX
   - **Variable Value:**  C:\Program Files\Tesseract-OCR\

## 🚀 How to Run the Project Locally
1. run testng.xml suite from IDE (Right-click → Run as TestNG Suite).

### 📸 OUTPUT
1. Voter roll PDFs downloaded to your configured folder.
2. HTML reports generated post-execution.
3. Screenshots of failed test cases saved for debugging.
