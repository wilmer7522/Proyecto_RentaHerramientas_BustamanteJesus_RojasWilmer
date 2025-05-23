<template>
  <div class="container">
    <div class="main-screen">
      <div class="header">

        <a href="http://localhost:5173/"><img id="logo" src="/src/assets/header/LogoSinFondo.png" alt="Logo" /></a>
        
        <input v-model="filtro" id="categoria" type="text" placeholder="Herramientas" />
        <button id="buttonherramienta" @click="cargarHerramientas">Buscar</button>

        <div class="sidebar">

          <label class="burger" for="burger">
            <input type="checkbox" id="burger">
            <span></span>
            <span></span>
            <span></span>
          </label>

        </div>

        <div class="iconheader">
          <button id="login">Iniciar Sesión</button>
          <!-- <button><img src="./assets/header/user.png" alt=""></button>
          <button><img src="./assets/header/heart.png" alt=""></button>
          <button><img src="./assets/header/shopping-bag.png" alt=""></button> -->
        </div>
      </div>

      <div class="presentation">

        <div class="slogan">
          <p>Perfecta<span>/</span><br>
            Al mejor precio<span>/</span><br>
            Cuando la necesites
          </p>
        </div>

        <img id="collage" src="./assets/img/collage2.svg" alt="">

        <div class="register">
          <input id="inputregister" type="text" placeholder="Correo Electrónico">
          <button id="buttonRegister" @click="registrarCliente">Registrarme ahora</button>
        </div>

      </div>

    </div>

    <div class="info" ref="zonaHerramientas">

      <div class="filter">
        <button id="filterbutton" @click="toggleCategorias">
          <img id="filtericon" src="./assets/icons/menu-filter.png" alt="">
          <p id="tittlecategoria">Categoría</p>
        </button>

        <div v-if="mostrarCategorias" class="barra-categorias">
          <button :class="{ activo: categoriaSeleccionada === null }" @click="mostrarTodas">
            Todos
          </button>

          <button v-for="categoria in categorias" :key="categoria.id"
            :class="{ activo: categoriaSeleccionada === categoria.id }" @click="filtrarPorCategoria(categoria.id)">
            {{ categoria.nombre }}
          </button>
        </div>

      </div>

      <div class="herramientas">

        <div class="card" v-for="h in herramientasPaginadas" :key="h.id">

          <div class="imagen">
            <img :src="h.fotourl" alt="Imagen de herramienta" />
          </div>
          
          <h3>{{ h.nombre }}</h3>
          <p>{{ h.descripcion }}</p>
          <p><strong>Costo por día: COP </strong> {{ h.costo_dia }}</p>

        </div>

      </div>

      <div class="paginacion" v-if="totalPaginas > 1">

        <button id="previous" @click="anteriorPagina" v-if="paginaActual > 1">
          <span><img id="left" src="./assets/icons/left.png" alt=""></span> Página anterior
        </button>

        <button id="next" @click="siguientePagina" v-if="paginaActual < totalPaginas">
          Página siguiente <span><img id="right" src="./assets/icons/right.png" alt=""></span>
        </button>

      </div>

      <footer>

        <div class="brand">
          <img id="logoicon" src="./assets/icons/logo.png" alt="">
          <p><span>RENTA</span> <br> HERRAMIENTA</p>
        </div>

        <div class="footer-separador1"></div>

        <div class="updates">
          <span>Mantente actualizado</span>
          <div class="email">
            <input id="correo" type="email" placeholder="Correo Electrónico">
            <button id="enviar">Enviar</button>
          </div>
        </div>

        <div class="services">

          <span>Servicios</span>
          <button id="button1">
            <img id="arrow" src="./assets/icons/yellowarrow.png" alt="">
            <p>Reserva</p>
          </button>
          <button>
            <img id="arrow" src="./assets/icons/yellowarrow.png" alt="">
            <p>Alquiler</p>
          </button>
          <button>
            <img id="arrow" src="./assets/icons/yellowarrow.png" alt="">
            <p>Cotización</p>
          </button>

        </div>

        <div class="help">
          <span>Ayuda</span>
          <button>
            <img id="arrow" src="./assets/icons/yellowarrow.png" alt="">
            <p>Contáctanos</p>
          </button>
        </div>

        <div class="moreinfo">
          <span>Más información</span>
          <p>315 990 3567</p>
          <p>contacto@rentaherramienta.com</p>
          <p>Norte de Santander, Colombia</p>
          <p>Lun a Dom - 9:00 am a 8:00 pm</p>
        </div>

        <div class="footer-separador2"></div>

        <div class="derechos">
          <p>Desarrollado por Jesús Bustamante y Wilmer Rojas</p>
          <p id="copy">&copy;<span id="year">year</span> Renta Herramienta. Todos los derechos reservados.</p>
        </div>
        
      </footer>
    </div>

  </div>

</template>



<script setup>
import { ref, computed, onMounted, nextTick } from 'vue'

const herramientas = ref([])
const filtro = ref('')
const paginaActual = ref(1)
const herramientasPorPagina = 12
const zonaHerramientas = ref(null)

const mostrarCategorias = ref(false)
const categorias = ref([])
const categoriaSeleccionada = ref(null) // Aquí guardaremos el ID

// Herramientas visibles por página
const herramientasPaginadas = computed(() => {
  const inicio = (paginaActual.value - 1) * herramientasPorPagina
  const fin = inicio + herramientasPorPagina
  return herramientas.value.slice(inicio, fin)
})

const totalPaginas = computed(() => {
  return Math.ceil(herramientas.value.length / herramientasPorPagina)
})

window.addEventListener('DOMContentLoaded', () => {
  const yearElement = document.getElementById('year');
  if (yearElement) {
    yearElement.textContent = new Date().getFullYear();
  }
});

function siguientePagina() {
  if (paginaActual.value < totalPaginas.value) paginaActual.value++
}

function anteriorPagina() {
  if (paginaActual.value > 1) paginaActual.value--
}


// Cargar herramientas desde el backend
async function cargarHerramientas() {
  let url = 'http://localhost:8080/tools'

  try {
    // Si hay filtro por nombre
    if (filtro.value.trim() !== '') {
      url += `/buscar?nombre=${encodeURIComponent(filtro.value)}`
    }
    // Si hay categoría seleccionada (por ID)
    else if (categoriaSeleccionada.value !== null) {
      url += `/categoria/${categoriaSeleccionada.value}`
    }

    const res = await fetch(url)
    herramientas.value = await res.json()

    if (herramientas.value.length && zonaHerramientas.value) {
      nextTick(() => {
        zonaHerramientas.value.scrollIntoView({ behavior: 'smooth' })
      })
    }

    paginaActual.value = 1
  } catch (error) {
    console.error('Error al cargar herramientas:', error)
  }
}

// Cargar categorías
async function cargarCategorias() {
  try {
    const res = await fetch('http://localhost:8080/api/categorias')
    categorias.value = await res.json()
  } catch (error) {
    console.error('Error al cargar categorías:', error)
  }
}

// Filtrar por categoría
function filtrarPorCategoria(idCategoria) {
  categoriaSeleccionada.value = idCategoria
  filtro.value = '' // Reiniciar búsqueda textual
  cargarHerramientas()
}

function mostrarTodas() {
  categoriaSeleccionada.value = null
  filtro.value = ''
  cargarHerramientas()
}

function toggleCategorias() {
  mostrarCategorias.value = !mostrarCategorias.value
}

// Al montar
onMounted(() => {
  cargarHerramientas()
  cargarCategorias()
})

</script>

<style scoped>
  @font-face {
  font-family: "Monserrat Bold";
  src: url(./assets/fonts/Montserrat-Bold.ttf);
}

@font-face {
  font-family: "Inter Light";
  src: url(./assets/fonts/Inter_28pt-Light.ttf);
}

@font-face {
  font-family: "Monserrat Light";
  src: url(./assets/fonts/Montserrat-Light.ttf);
}

@font-face {
  font-family: "Monserrat Regular";
  src: url(./assets/fonts/Montserrat-Regular.ttf);
}

@font-face {
  font-family: "Monserrat SemiBold";
  src: url(./assets/fonts/Montserrat-SemiBold.ttf);
}

.main-screen {
  position: absolute;
  left: 0;
  top: 0;
  width: 100%;
  height: 100vh;
  background-color: #F1F5F9;
}

.header {
  position: sticky;
  top: 0;
  height: 5vw;
  width: 100%;
  background-color: rgba(9, 14, 38, 0.5);
  backdrop-filter: blur(10px);
  -webkit-backdrop-filter: blur(10px);
  display: flex;
  align-items: center;
  border-bottom: 1px solid rgba(255, 255, 255, 0.1);
  z-index: 1;
}

#logo {
  width: 10vw;
  height: auto;
  margin-left: 10vw
}

#categoria {
  width: 40vw;
  height: 3vw;
  margin-left: 2vw;
  text-indent: 1vw;
  border-radius: 0.5vw;
  border-style: none;
  font-size: 1vw;
  font-family: "Inter Light";
}

#buttonherramienta {
  height: 3vw;
  width: 5vw;
  background-color: #fabc11;
  color: black;
  border: none;
  border-radius: 0.5vw;
  border: 0.1vw solid #fabc11;
  cursor: pointer;
  margin-left: 10px;
  font-size: 1vw;
  font-family: "Inter Light";
  display: flex;
  align-items: center;
  justify-content: center;
}

.burger {
  position: absolute;
  top: 1.5vw;
  left: 2vw;
  width: 2.5vw;
  height: 2vw;
  background: transparent;
  cursor: pointer;
  display: block;
  z-index: 2;
}

.burger input {
  display: none;
}

.burger span {
  display: block;
  position: absolute;
  height: 4px;
  width: 100%;
  background: #fabc11;
  border-radius: 9px;
  opacity: 1;
  left: 0;
  transform: rotate(0deg);
  transition: .25s ease-in-out;
}

.burger span:nth-of-type(1) {
  top: 0px;
  transform-origin: left center;
}

.burger span:nth-of-type(2) {
  top: 50%;
  transform: translateY(-50%);
  transform-origin: left center;
}

.burger span:nth-of-type(3) {
  top: 100%;
  transform-origin: left center;
  transform: translateY(-100%);
}

.burger input:checked ~ span:nth-of-type(1) {
  transform: rotate(45deg);
  top: 0px;
  left: 5px;
}

.burger input:checked ~ span:nth-of-type(2) {
  width: 0%;
  opacity: 0;
}

.burger input:checked ~ span:nth-of-type(3) {
  transform: rotate(-45deg);
  top: 28px;
  left: 5px;
}

.iconheader {
  display: flex;
  align-items: center;
  margin-left: 13.7vw;
}

/* .iconheader img {
  width: 2vw;
} */

.iconheader button {
  background-color: transparent;
  border: 0.1vw solid #7178b0;
  border-radius: 0.5vw;
  cursor: pointer;
  font-size: 1vw;
  font-family: "Monserrat SemiBold";
  color: #7178b0;
  width: 8vw;
  height: 2vw;
}

.iconheader button:hover {
  background-color: #fabc11;
  color: #000;
}

.presentation {
  position: absolute;
  top: 0;
  width: 100%;
  height: 100vh;
  background-color: #090e26;
  background-image: radial-gradient(
    circle at top,
    rgba(101, 91, 238, 0.15),
    rgba(9, 14, 38, 1)
  );
  background-repeat: no-repeat;
  background-size: cover;
  color: #f1f5f9;
  font-family: 'Segoe UI', sans-serif;
}

.slogan {
  margin-top: 10vw;
  margin-left: 10vw;
}

.slogan p {
  font-size: 4vw;
  font-family: "Monserrat Bold";
  z-index: 1;
}

.slogan span {
  color: #fabc11;
}

#collage {
  position: absolute;
  width: 50vw;
  top: 10vw;
  right: 0vw;
  opacity: 1;
  filter: drop-shadow(0 0 10px rgba(0, 0, 0, .8));
}

.register {
  display: flex;
  align-items: center;
  margin-left: 10vw;
}

#inputregister {
  width: 30vw;
  height: 3vw;
  text-indent: 1vw;
  font-size: 1vw;
  font-family: "Inter Light";
  border-style: none;
  border-radius: 0.5vw;
}

#buttonRegister {
  display: flex;
  align-items: center;
  padding: 6px 12px;
  margin-left: 1vw;
  height: 3vw;
  width: 7vw;
  background-color: #fabc11;
  color: black;
  border: none;
  border-radius: 0.5vw;
  border: 0.1vw solid #fabc11;
  cursor: pointer;
  font-size: 1vw;
  font-family: "Inter Light";
}

.info {
  position: absolute;
  width: 100%;
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
  top: 47.5vw;
  left: 0;
  background-color: #0d122e;
}

.filter {
  position: absolute;
  display: flex;
  align-items: center;
  left: 10vw;
  top: 4vw;
}

#filterbutton {
  cursor: pointer;
  display: flex;
  align-items: center;
  gap: 1vw;
  border: none;
  background-color: transparent;
}

#tittlecategoria {
  font-size: 1vw;
  font-family: "Monserrat Light";
  color: white;
}

#filtericon {
  width: 2vw;
  height: 2vw;
}

.barra-categorias {
  width: auto;
  display: flex;
  overflow-x: auto;
  gap: 1rem;
  padding: 10px;
  color: black;
}

.barra-categorias button.activo {
  background-color: #fabc11;
  color: black;
  font-weight: bold;
}

.barra-categorias button {
  border-radius: 1vw;
  border: 0.1vw solid black;
  font-family: "Monserrat Light";
  font-size: 0.8vw;
  transition: font-size 0.5s;
  background-color: #7178b0;
  height: 2vw;
}

.barra-categorias button:hover {
  background-color: #fabc11;
  font-size: 1vw;
}


.herramientas {
  margin-top: 10vw;
  width: 80%;
  display: grid;
  grid-template-columns: repeat(auto-fill, minmax(250px, 1fr));
  gap: 16px;
}

.card {
  background: #f9fafb;
  border: 1px solid #e5e7eb;
  border-radius: 10px;
  padding: 16px;
  box-shadow: 0 2px 4px rgba(0, 0, 0, 0.05);
  transition: transform 0.2s ease;
}

.imagen {
  width: 100%;
  display: flex;
  justify-content: center;
  border-bottom: 0.1vw solid #9da4b0;
}

.imagen img {
  border-radius: 0.5vw;
  width: 10vw;
  height: 10vw;
}

.card h3, .card strong {
  font-family: "Monserrat Bold";
}

.card p {
  font-family: "Monserrat Light";
}

.card:hover {
  transform: translateY(-4px);
}

.paginacion {
  width: 80%;
  margin-top: 3vw;
  display: flex;
  justify-content: space-between;
}

.paginacion button {
  background-color: #f1f5f9;
  border: none;
  padding: 10px 20px;
  border-radius: 8px;
  transition: background-color 0.2s ease;
  cursor: pointer;
}

.paginacion button:active {
  background-color: #fabc11; /* Color temporal al presionar */
  transform: scale(0.98); /* Leve efecto de clic */
}


#previous {
  display: flex;
  justify-content: center;
  align-items: center;
  width: 20vw;
  height: 3vw;
  border-radius: 0.5vw;
  color: #9da4b0;
  font-family: "Monserrat Bold";
  font-size: 1vw;
}

#left, #right {
  margin-top: 0.4vw;
  width: 2vw;
}

#next {
  display: flex;
  justify-content: center;
  align-items: center;
  width: 20vw;
  height: 3vw;
  border-radius: 0.5vw;
  color: #9da4b0;
  font-family: "Monserrat Bold";
  font-size: 1vw;
}

footer {
  margin-top: 5vw;
  background-color: #090e26;
  background-image: linear-gradient(
    to top,
    #0f1c4f,
    #0d122e
  );
  width: 100%;
  border-left: 2vw;
  border-right: 2vw;
  border-bottom: 0.1vw solid #ccc;
  height: 33vw;
  top: 135vw;
  left: 0;
}

.footer-separador1 {
  margin-top: 1vw;
  margin-left: 10vw;
  width: 80%;
  height: 0.1vw;
  background-color: #292f54;
}

.footer-separador2 {
  margin-top: 10vw;
  margin-left: 10vw;
  width: 80%;
  height: 0.1vw;
  background-color: #292f54;
}

.brand {
  margin-left: 10vw;
  display: flex;
  align-items: center;
  gap: 1vw;
  font-family: "Monserrat Bold";
  font-size: 1vw;
  color: white;
}

.brand span {
  color: #fabc11;
}

#logoicon {
  width: 2.5vw;
  border-radius: 0.5vw;
}

.derechos {
  width: 80%;
  margin-left: 10vw;
  display: flex;
  align-items: center;
  justify-content: space-between;
  color: #7178b0;
  font-size: 0.9vw;
  font-family: "Monserrat Light";
}

.services {
  display: flex;
  flex-direction: column;
  justify-content: start;
  margin-top: -4.3vw;
  margin-left: 42vw;
}

.services button {
  background-color: transparent;
  display: flex;
  align-items: center;
  width: 7vw;
  height: 2.5vw;
  cursor: pointer;
  border: none;
  font-size: 1.1vw;
  font-family: "Monserrat SemiBold";
  color: #e9e8ff;
}

.services span {
  margin-left: 0.5vw;
  font-size: 1vw;
  font-family: "Monserrat Bold";
  color: #7178b0;
}

.services p {
  margin-left: 0.7vw;
}

#button1 {
  margin-top: 1vw;
}

#arrow {
  transition: transform 0.3s ease;
  width: 2vw;
  background-color: #1c1f4d;
  border-radius: 1vw;
}

.services button:hover #arrow {
  transform: rotate(45deg);
}

.help {
  display: flex;
  flex-direction: column;
  margin-left: 55vw;
  margin-top: -9.7vw;
}

.help button {
  background-color: transparent;
  display: flex;
  align-items: center;
  width: 7vw;
  height: 2.5vw;
  cursor: pointer;
  border: none;
  font-size: 1.1vw;
  font-family: "Monserrat SemiBold";
  color: #e9e8ff;
  margin-top: 1vw;
}

.help span {
  margin-left: 0.5vw;
  font-size: 1vw;
  font-family: "Monserrat Bold";
  color: #7178b0;
}

.help p {
  margin-left: 0.7vw;
}

.help button:hover #arrow {
  transform: rotate(45deg);
}

.moreinfo {
  margin-top: -4.8vw;
  margin-left: 70vw;
  font-size: 1.1vw;
  font-family: "Monserrat SemiBold";
  color: #e9e8ff;
}

.moreinfo span {
  font-size: 1vw;
  color: #7178b0;
  font-family: "Monserrat Bold";
}

.updates {
  margin-top: 2vw;
  margin-left: 10vw;
}

.updates span {
  font-size: 1vw;
  color: #7178b0;
  font-family: "Monserrat Bold";
}

.email {
  margin-top: 1vw;
}

#correo {
  width: 20vw;
  height: 2vw;
  border-radius: 0.5vw;
  border: 0.1vw solid #7178b0;
  background-color: transparent;
  text-indent: 1vw;
  font-size: 1vw;
  font-family: "Inter Light";
  color: #7178b0;
}

#enviar {
  background-color: #fabc11;
  border-radius: 0.5vw;
  font-size: 1vw;
  font-family: "Inter Light";
  margin-left: 0.5vw;
  height: 2vw;
  border: none;
  cursor: pointer;
  color: #000;
}
</style>