# ioka-android-sdk 🚀
[![](https://jitpack.io/v/iokadev/ioka-android-sdk.svg)](https://jitpack.io/#iokadev/ioka-android-sdk)

**ioka Android SDK** дает возможность легко и быстро подключать оплату через ioka.kz в Android-проектах.

# Функционал

- **Сохранение карты пользователя**

Данная версия SDK поддерживает возможность сохранить карту пользователя, список сохраненных карт пользователя и удаление сохраненной карты пользователя. Для запуска флоу сохранения карты, вызовите следующий метод:
```kotlin
// customerToken получаем из сервиса мерчанта
// Configuration - это класс для кастомизации UI-компонентов
Ioka.startSaveCardFlow(customerToken: String, configuration: Configuration?): (Activity) -> Unit
```
Для получения списка сохраненных карт пользователя, вызовите следующий `suspend` метод:
```kotlin
Ioka.getCards(customerToken: String): List<CardModel>
```
Для удаления сохраненной карты пользователя, вызовите следующий `suspend` метод:
```kotlin
// cardId достаем из CardModel
// Возвращает Boolean со значением: true, если удаление успешно; false, если нет
Ioka.removeCard(customerToken: String, cardId: String): Boolean
```

- **Стандартный флоу оплаты**
```kotlin
// orderToken получаем из сервиса мерчанта
// Configuration - это класс для кастомизации UI-компонентов
Ioka.startPaymentFlow(orderToken: String, configuration: Configuration? = null): (Activity) -> Unit
```

- **Оплата сохраненной картой**
```kotlin
// orderToken получаем из сервиса мерчанта
// CardDvo - класс, который используется для отображения информации по карте
// Configuration - класс для кастомизации UI-компонентов
Ioka.startPaymentWithSavedCardFlow(orderToken: String, card: CardDvo, configuration: Configuration? = null): (Activity) -> Unit
```

## Публичные классы SDK
Класс Configuration используется для кастомизации UI-компонентов страницы флоу:
```kotlin
// Если передать null, ставится значения по умолчанию из ресурсов ioka SDK
data class Configuration(
  // Текст на кнопке (оплаты, привязки и т.д.)
  val buttonText: String? = null,
  // Бэкграунд для EditText полей, принимает в себя @DrawableRes Int
  val fieldBackground: Int? = null,
  // Бэкграунд для кнопок (если она есть), принимает в себя @DrawableRes Int
  val buttonBackground: Int? = null,
  // Бэкграунд-цвет страницы, принимает в себя @ColorRes Int
  val backgroundColor: Int = R.color.ioka_color_background,
  // Цвет иконок (сканирования, CVV и т.д.), принимает в себя @ColorRes Int
  val iconColor: Int = R.color.ioka_color_icon_secondary,
  // Цвет текстов, принимает в себя @ColorRes Int
  val textColor: Int = R.color.ioka_color_text,
  // Цвет подсказок в EditText полях (если они есть), принимает в себя @ColorRes Int
  val hintColor: Int = R.color.ioka_color_nonadaptable_grey,
)
```
Класс CardModel используется для того, чтобы передать список сохраненных карт пользователя:
```kotlin
data class CardModel(
  // Идентификатор карты
  val id: String?,
  // Идентификатор клиента, к которому привязана карта
  val customerId: String?,
  // Время привязки карты к пользователю
  val createdAt: String?,
  // Замаскированный PAN-номер карты
  val panMasked: String?,
  // Срок годности карты
  val expiryDate: String?,
  // Имя обладателя карты
  val holder: String?,
  // Платежная система карты (VISA, MASTERCARD, и т.д.)
  val paymentSystem: String?,
  // Банк-эмиттент привязанной карты (Alfa, Kaspi, и т.д.)
  val emitter: String?,
  // Флаг, который определяет нужно ли подтверждение CVV для оплаты данной картой
  // Используется во флоу оплаты сохраненной картой
  val cvcRequired: Boolean?,
)
```
Класс CardDvo используется для отображения информации о карте: (применяется во флоу оплаты сохраненной картой)
```kotlin
data class CardDvo(
  // Идентификатор карты
  val cardId: String,
  // Замаскированный PAN-номер карты
  val cardNumber: String,
  // Код платежной системы карты (получать из CardModel.paymentSystem)
  val cardType: String,
  // Флаг, который определяет нужно ли подтверждение CVV для оплаты данной картой
  val cvvRequired: Boolean,
)
```

# Технический стек библиотеки 📘
- Kotlin
- MVVM
- Coroutines
- OkHttp
- Retrofit

# Подключение библиотеки 🔗
## Требования
- Android minSdkVersion 21, targetSdkVersion 31
- Android Gradle Plugin 1.6.10
- Gradle 7.0.4
- AndroidX

## Подключение через Gradle

### В файле `build.gradle` в root-директории добавьте следующее: 
- для Gradle 6.8 и выше:
```Gradle
dependencyResolutionManagement {
    repositories {
        ...
        maven { url 'https://jitpack.io' }
    }
}
```
- для Gradle ниже 6.8:
```Gradle
allprojects {
  repositories {
    ...
    maven { url 'https://jitpack.io' }
  }
}
```
### В файле `build.gradle` в пакете модуля:
```Gradle
dependencies {
  implementation 'com.github.iokadev:ioka-android-sdk:<LATEST_ACTUAL_VERSION>'
}
```
# Пример использования 🤖
[Демо-проект с использованием ioka Android SDK](https://github.com/iokadev/ioka-android)
