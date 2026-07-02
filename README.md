````md
# Backend Ocupacional

Backend del sistema de gestión de Salud Ocupacional, desarrollado con Node.js, Express y TypeScript. Proporciona una API REST encargada de la autenticación de usuarios, la gestión de empresas, trabajadores, evaluaciones médicas y demás recursos del sistema, garantizando una arquitectura escalable, segura y mantenible.

---

## Descripción

Este proyecto implementa la lógica de negocio y la capa de acceso a datos del sistema de Salud Ocupacional. La API expone diferentes servicios REST consumidos por el frontend, gestionando la autenticación, autorización y operaciones sobre la base de datos.

---

## Tecnologías

- Node.js
- Express.js
- TypeScript
- Prisma ORM
- PostgreSQL
- JWT Authentication
- Bcrypt
- Zod
- CORS
- Dotenv

---

## Arquitectura del proyecto

```
src/
├── config/
├── controllers/
├── middlewares/
├── prisma/
├── routes/
├── services/
├── utils/
├── validations/
├── app.ts
└── server.ts
```

La arquitectura sigue una separación por responsabilidades, permitiendo desacoplar la lógica de negocio, el acceso a datos, las validaciones y la configuración del servidor.

---

## Requisitos

Antes de ejecutar el proyecto asegúrese de contar con:

- Node.js 20 o superior
- npm
- PostgreSQL
- Git

Verificar instalación:

```bash
node -v
npm -v
```

---

## Instalación

Clonar el repositorio

```bash
git clone https://github.com/Edward23-ux/backend-ocupacional.git
```

Ingresar al directorio

```bash
cd backend-ocupacional
```

Instalar dependencias

```bash
npm install
```

---

## Configuración

Crear un archivo `.env` en la raíz del proyecto.

Ejemplo:

```env
DATABASE_URL="postgresql://usuario:password@localhost:5432/ocupacional"

JWT_SECRET="tu_clave_secreta"

PORT=3000
```

> Ajuste los valores de acuerdo con su entorno de desarrollo o producción.

---

## Base de datos

Ejecutar las migraciones:

```bash
npx prisma migrate dev
```

Generar el cliente de Prisma:

```bash
npx prisma generate
```

En caso de requerir datos iniciales:

```bash
npx prisma db seed
```

---

## Ejecución

Modo desarrollo

```bash
npm run dev
```

Modo producción

```bash
npm run build

npm start
```

---

## Scripts disponibles

| Comando | Descripción |
|----------|-------------|
| npm run dev | Ejecuta el servidor en modo desarrollo |
| npm run build | Compila el proyecto TypeScript |
| npm start | Ejecuta la versión compilada |
| npm run lint | Analiza el código mediante ESLint |
| npx prisma migrate dev | Ejecuta migraciones |
| npx prisma generate | Genera el cliente Prisma |

---

## Funcionalidades

- Autenticación mediante JWT.
- Gestión de usuarios.
- Gestión de empresas.
- Gestión de trabajadores.
- Gestión de evaluaciones ocupacionales.
- Validación de datos.
- Protección de rutas.
- Encriptación de contraseñas.
- API REST.
- Integración con base de datos PostgreSQL.

---

## Estructura de la API

La API está organizada mediante controladores, servicios y rutas independientes para facilitar la escalabilidad del proyecto.

Ejemplo de organización:

```
Request
   │
   ▼
Routes
   │
   ▼
Middlewares
   │
   ▼
Controllers
   │
   ▼
Services
   │
   ▼
Prisma ORM
   │
   ▼
PostgreSQL
```

---

## Integración con Frontend

Este backend es consumido por la aplicación Frontend Ocupacional.

Repositorio relacionado:

```
https://github.com/Edward23-ux/frontend-ocupacional
```

---

## Buenas prácticas implementadas

- Arquitectura en capas.
- Principio de responsabilidad única.
- Validación de datos de entrada.
- Variables de entorno.
- Manejo centralizado de errores.
- Tipado estricto con TypeScript.
- Separación entre controladores y servicios.
- Acceso a datos mediante Prisma ORM.
- Autenticación basada en JWT.
- Código modular y escalable.

---

## Equipo de desarrollo

Proyecto desarrollado como parte del curso de Ingeniería de Software.



## Licencia

Este proyecto fue desarrollado con fines académicos y educativos.
````
