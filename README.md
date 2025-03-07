
# Estándar de Codificación para Kotlin

## 1. Nombres de Variables y Constantes

-   Todos los nombres deben estar en inglés.
-   Usar **camelCase** para variables.
-   Usar **SCREAMING_SNAKE_CASE** para constantes.

```kotlin
var userName = "JohnDoe"
var maxRetries = 3
var isLoggedIn = false

const val MAX_ATTEMPTS = 5
const val API_BASE_URL = "https://example.com/api"
```

## 2. Nombres de Funciones

-   Usar **camelCase**.
-   El nombre debe describir claramente su propósito.
-   Si la función realiza una acción, el nombre debe iniciar con un verbo.

```kotlin
fun getUserProfile(): UserModel { ... }
fun fetchUserData() { ... }
fun calculateTotal(price: Double, tax: Double): Double { ... }
```

## 3. Nombres de Clases

-   Usar **PascalCase**.
-   Evitar abreviaciones y utilizar nombres descriptivos.

```kotlin
class UserModel { ... }
class OrderViewModel { ... }
class ProductView { ... }
```

## 4. Naming Conventions para Arquitectura MVVM

-   Vistas terminan con `View`.
-   Modelos terminan con `Model`.
-   ViewModels terminan con `ViewModel`.

```kotlin
class LoginView { ... }
class UserModel { ... }
class LoginViewModel { ... }
```

## 5. Uso de Tipos de Datos

-   Evitar tipos primitivos innecesarios y utilizar los adecuados.
-   Preferir `val` sobre `var` si el valor no cambia.

```kotlin
val userName: String = "John"
var userAge: Int = 25
val isPremiumUser: Boolean = true
```

## 6. Formato y Estilo de Código

-   Usar el **formateador de código de Android Studio** para mantener un código limpio.
-   **Atajo para formatear código:**

-   Windows/Linux: Ctrl + Alt + L
-   macOS: Cmd + Option + L

```kotlin
if (isLoggedIn) {
    showWelcomeMessage()
} else {
    requestLogin()
}
```

## 7. Comentarios y Documentación

-   Usar `//` para comentarios cortos.
-   Usar `/** ... */` para documentar funciones y clases.

```kotlin
/**
 * Calculates the total price including tax.
 *
 * @param price Base price of the product
 * @param tax Percentage of tax applied
 * @return Total price after tax
 */
fun calculateTotal(price: Double, tax: Double): Double {
    return price * (1 + tax / 100)
}
```

## 8. Organización del Código

-   Declarar variables antes de las funciones.
-   Agrupar funciones relacionadas.

## 9. Nombres para Assets de Imágenes

-   Usar nombres en inglés.
-   Solo minúsculas y guiones bajos (`_`).
-   No usar espacios, caracteres especiales ni mayúsculas.

### ✅ Ejemplo de nombres correctos:

-   `icon_profile.png`
-   `bg_splash_screen.jpg`
-   `btn_login_pressed.png`
-   `banner_home.png`
-   `ic_settings.xml`

### ❌ Ejemplo de nombres incorrectos:

-   `IconProfile.png` (No usar mayúsculas)
-   `BG-SPLASH.JPG` (No usar guiones medios)
-   `button login.png` (No usar espacios)
-   `SettingsIcon.xml` (No usar PascalCase)

### 📌 Convenciones recomendadas según tipo de imagen:

Prefijo

Uso

Ejemplo

`ic_`

Íconos

`ic_home.png`, `ic_settings.xml`

`bg_`

Fondos

`bg_splash_screen.jpg`

`btn_`

Botones

`btn_login_pressed.png`

`banner_`

Banners

`banner_home.png`

## ✅ Resumen del Estándar de Codificación

-   Seguir las convenciones de nomenclatura para variables, clases y assets.
-   Usar el formateador de código de Android Studio.
-   Documentar funciones con comentarios.
-   Organizar el código correctamente.
