# Gauge Test Automation Framework

Java, Gauge, Selenium və REST Assured ilə hazırlanmış **UI** və **API** test avtomatlaşdırma layihəsi.

## 📋 İçindəkilər

- [Layihə haqqında](#-layihə-haqqında)
- [Texnologiyalar](#️-texnologiyalar)
- [Layihə strukturu](#-layihə-strukturu)
- [Quraşdırma](#-quraşdırma)
- [Testlərin işə salınması](#️-testlərin-işə-salınması)
- [Test yazılması](#-test-yazılması)
- [Konfiqurasiya](#️-konfiqurasiya)

##  Layihə haqqında

Bu framework 2 əsas funksionallığı dəstəkləyir:

###  UI Testləri
- Login funksionallığının test edilməsi
- Element ilə əlaqə (click, type, scroll)
- Səhifə elementlərinin yoxlanması
- Page Object Model strukturu

###  API Testləri
- GET, POST, PUT, PATCH sorğuları
- JSON body ilə işləmək
- Header yoxlamaları
- Response validasiyası

##  Texnologiyalar

| Texnologiya | Versiya | İstifadə məqsədi |
|-------------|---------|------------------|
| Java | 11 | Əsas proqramlaşdırma dili |
| Maven | 3.x | Build və asılılıq idarəetməsi |
| Gauge | 0.11.3 | BDD test framework |
| Selenium WebDriver | 4.28.1 | UI avtomatlaşdırması |
| WebDriverManager | 5.7.0 | Browser driver idarəetməsi |
| REST Assured | 5.5.0 | API testləşdirilməsi |
| AssertJ | 3.17.2 | Assertion library |

##  Layihə strukturu
```
Gauge-Test-Automation-/
│
├── specs/                              # Test ssenariləri
│   ├── UI Test Scenario/
│   │   ├── consept-ui-test/
│   │   │   └── login.cpt              # Login konsepti
│   │   └── loginTest.spec             # Login test ssenarileri
│   └── api-testing/
│       ├── getApiTesting.spec         # GET API testləri
│       └── postPutPatch_apiTesting.spec # POST/PUT/PATCH testləri
│
├── src/test/java/
│   ├── helper/                         # Köməkçi metodlar
│   │   ├── ApiHelper.java
│   │   ├── ClickHelper.java
│   │   ├── ScrollHelper.java
│   │   ├── TypeHelper.java
│   │   └── VerifyHelper.java
│   │
│   ├── imp/                            # Step implementasiyaları
│   │   ├── ApiRequestImp.java         # API addımları
│   │   ├── BrowserImp.java            # Browser əməliyyatları
│   │   ├── ClickElementImp.java       # Click əməliyyatları
│   │   ├── HeaderImp.java             # Header əməliyyatları
│   │   ├── ScrollImp.java             # Scroll əməliyyatları
│   │   ├── TypeElementImp.java        # Yazma əməliyyatları
│   │   └── VerifyElementImp.java      # Yoxlama əməliyyatları
│   │
│   └── utils/                          # Utility sinifləri
│       ├── DriverManager.java         # WebDriver idarəetməsi
│       └── LocatorReader.java         # Locator oxuma
│
├── src/test/resources/
│   ├── body/                           # API request body-ləri
│   │   ├── created-user.json
│   │   ├── full-update-post.json
│   │   ├── update-post-user1.json
│   │   └── valid-post-user.json
│   │
│   └── locator/                        # UI element locatorları
│       └── loginTest.json
│
├── env/                                # Environment konfiqurasiyaları
│   └── default/
│       ├── default.properties
│       └── java.properties
│
├── manifest.json                       # Gauge manifest
├── pom.xml                            # Maven asılılıqları
└── README.md
```

##  Quraşdırma

### 1️⃣ Tələblər

Kompyuterdə quraşdırılmalıdır:

- ☕ **Java JDK 11+** → [Yüklə](https://www.oracle.com/java/technologies/javase-jdk11-downloads.html)
- 📦 **Maven 3.6+** → [Yüklə](https://maven.apache.org/download.cgi)
- 📊 **Gauge** → [Yüklə](https://docs.gauge.org/getting_started/installing-gauge.html)
- 🌐 **Chrome/Firefox Browser**

## 🤝 Contribute etmək

1. Fork edin
2. Feature branch yaradın: `git checkout -b feature/yenilik`
3. Dəyişiklikləri commit edin: `git commit -m 'Yeni feature'`
4. Branch-i push edin: `git push origin feature/yenilik`
5. Pull Request açın

## 📚 Faydalı resurlar

- [Gauge Documentation](https://docs.gauge.org/)
- [Selenium WebDriver](https://www.selenium.dev/documentation/)
- [REST Assured](https://rest-assured.io/)
- [AssertJ](https://assertj.github.io/doc/)

## 📧 Əlaqə

Suallarınız olsa GitHub Issues-də yaza bilərsiniz.



**Made with ❤️ using Gauge Framework**

⭐ Layihəni bəyənsəniz, star verməyi unutmayın!
