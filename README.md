# Дипломная работа: тестирование мобильного приложения «Мобильный хоспис»

Репозиторий содержит:
- `Plan.md` — план проверки и автоматизации;
- `docs/Check.csv` — чек-лист;
- `docs/Cases.csv` — тест-кейсы;
- `fmh_android_updated/` — исходники приложения + UI-автотесты;
- `allure-results.zip` — отчёт Allure о прогоне тестов;
- `Result.md` — сравнение ручного и автоматизированного тестирования.

---

## Требования для запуска автотестов

1. **JDK 17+** (`JAVA_HOME` настроен).
2. **Android SDK** с платформой **API 36** (`ANDROID_HOME` настроен).
3. **Эмулятор** Android API 36 (например, `Hospice_API36`).
4. **Gradle wrapper** (входит в проект).

## Процедура запуска

### 1. Клонирование репозитория

```bash
git clone https://github.com/FatimaTkachenko/hospice-diploma.git
cd hospice-diploma/fmh_android_updated