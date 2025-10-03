# 🚗 ePass Bordumsa Admin

> **⚠️ Project Status: No Longer Maintained**  
> This project was developed during the COVID-19 pandemic in 2020 as part of the ePass system for managing travel permits in Bordumsa, Arunachal Pradesh. The project is no longer being actively maintained.

## 📱 Overview

**ePass Bordumsa Admin** is an Android application designed for administrators to manage travel permit applications during the COVID-19 pandemic. The app allows authorized officials to view analytics, approve or reject travel pass applications, and generate QR codes for approved permits.

## 🎯 Key Features

### 🔐 Authentication & Security
- Secure login system for authorized administrators
- Phone number and password-based authentication
- Session management with persistent login

### 📊 Dashboard & Analytics
- **Real-time Dashboard**: View pending and approved applications
- **Auto-refresh**: Automatically fetches new requests every 10 seconds
- **Analytics View**: Track application statistics and trends

### 📋 Application Management
- **New Requests**: View and process pending travel permit applications
- **Approved Applications**: Review previously approved permits
- **Detailed View**: Comprehensive permit details including:
  - Traveller information and ID proofs
  - Vehicle details and driver information
  - Route information and journey dates
  - Supporting documents
  - Terms and conditions

### ✅ Approval Workflow
- **Approve Permits**: One-click approval with automatic QR code generation
- **Reject Applications**: Reject with detailed reason specification
- **QR Code Generation**: Automatic QR code creation for approved permits
- **Digital Signatures**: Authority signature integration

### 📄 Document Management
- **Document Viewer**: View uploaded supporting documents
- **Image Viewer**: Full-screen image viewing with zoom capabilities
- **File Management**: Handle multiple document types

## 🏗️ Technical Architecture

### 📱 Platform & Framework
- **Platform**: Android (API Level 21+)
- **Language**: Java
- **Architecture**: MVVM with Fragment-based UI
- **Build System**: Gradle

### 🔧 Core Technologies
- **UI Framework**: AndroidX with Material Design
- **Network**: Retrofit 2.8.1 with OkHttp
- **Image Loading**: Picasso 2.7.18
- **QR Code**: ZXing Core 3.4.0
- **Data Binding**: Parceler for object serialization
- **HTTP Logging**: OkHttp Logging Interceptor

### 📦 Key Dependencies
```gradle
implementation 'androidx.appcompat:appcompat:1.1.0'
implementation 'com.google.android.material:material:1.1.0'
implementation 'com.squareup.retrofit2:converter-gson:2.8.1'
implementation 'com.squareup.picasso:picasso:2.71828'
implementation 'com.google.zxing:core:3.4.0'
implementation 'de.hdodenhof:circleimageview:3.1.0'
implementation 'com.github.chrisbanes:PhotoView:2.3.0'
```

### 🏛️ Project Structure
```
app/
├── src/main/java/com/jenbumapps/e_passbordumsa_admin/
│   ├── adapter/          # RecyclerView adapters
│   ├── app/             # Application class
│   ├── dialog/          # Custom dialogs
│   ├── frag/            # Fragment classes
│   ├── qrcode/          # QR code generation
│   ├── splash/          # Splash screen
│   └── utility/         # Utility functions
├── src/main/res/
│   ├── drawable/        # Vector drawables
│   ├── layout/          # XML layouts
│   └── values/          # Strings, colors, styles
└── core/                # Shared core module
```

## 🚀 Getting Started

### Prerequisites
- Android Studio 3.6.3 or higher
- Android SDK API Level 21+
- Java 8

### Installation
1. Clone the repository
2. Open the project in Android Studio
3. Sync Gradle dependencies
4. Build and run the application

### Configuration
The app requires backend API endpoints to be configured in the core module for:
- User authentication
- Application data fetching
- File upload services
- QR code generation

## 📱 Screenshots & UI

The app features a modern Material Design interface with:
- **Bottom Navigation**: Easy switching between New Requests, Approved, and Settings
- **Card-based Layout**: Clean presentation of permit information
- **Real-time Updates**: Automatic refresh for new applications
- **Responsive Design**: Optimized for various screen sizes

## 🔒 Permissions

The app requires the following permissions:
- `INTERNET` - For API communication
- `WRITE_EXTERNAL_STORAGE` - For QR code file generation
- `READ_EXTERNAL_STORAGE` - For file access
- `ACCESS_WIFI_STATE` - For network connectivity

## 🌍 COVID-19 Context

This application was developed during the COVID-19 pandemic in 2020 to facilitate:
- **Digital Travel Permits**: Replace paper-based permit systems
- **Contactless Processing**: Reduce physical contact during permit approval
- **Real-time Tracking**: Monitor travel movements during lockdown
- **QR Code Verification**: Enable quick verification at checkpoints

## 📊 Features in Detail

### For Administrators
- **Login System**: Secure authentication for authorized personnel
- **Application Review**: Detailed view of all permit applications
- **Approval/Rejection**: Quick decision-making with reason tracking
- **QR Code Management**: Automatic generation and management
- **Document Verification**: Review supporting documents and ID proofs

### For the System
- **Real-time Sync**: Automatic data synchronization
- **Offline Capability**: Basic functionality without internet
- **Security**: Encrypted data transmission
- **Audit Trail**: Complete history of all actions

## 🛠️ Development Notes

### Code Quality
- **Clean Architecture**: Separation of concerns with fragments and adapters
- **Error Handling**: Comprehensive error handling and user feedback
- **Performance**: Optimized image loading and memory management
- **Security**: Secure API communication and data storage

## 👨‍💻 Developer

**Developed by**: [[Mainong Jenbum](https://jenbum.com)]  
**Year**: 2020  
**Purpose**: COVID-19 Travel Pass Management  
**Location**: Changlang District, Arunachal Pradesh, India

---

## 🏥 Project Status

<div align="center">

![Status](https://img.shields.io/badge/Status-No%20Longer%20Maintained-red?style=for-the-badge)

**This project is no longer being maintained.**

*Developed during COVID-19 pandemic (2020) for travel pass management in Changlang District, Arunachal Pradesh. The application served its purpose during the pandemic and is now archived for reference.*

</div>

---

## 📞 Contact

For questions about this project or to discuss the implementation:

- **Email**: [mainong.jenbum@gmail.com]
- **GitHub**: [[@mainong-jenbum](https://github.com/mainong-jenbum)]
- **LinkedIn**: [[Mainong Jenbum](https://www.linkedin.com/in/mainongjenbum/)]

---

<div align="center">

**Made with ❤️ for the people of Changlang District, Arunachal Pradesh**

*During the challenging times of COVID-19 pandemic*

</div>
