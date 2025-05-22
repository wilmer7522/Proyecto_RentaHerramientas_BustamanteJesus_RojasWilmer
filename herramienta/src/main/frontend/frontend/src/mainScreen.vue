<template>
  <div class="container">
    <div class="main-screen">
      <div class="header">

        <img id="logo" src="/src/assets/header/LogoSinFondo.png" alt="Logo" />
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
          <button><img src="./assets/header/user.png" alt=""></button>
          <button><img src="./assets/header/heart.png" alt=""></button>
          <button><img src="./assets/header/shopping-bag.png" alt=""></button>
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

    <div class="info">

      <div class="filter">
        <button id="filterbutton" @click="toggleCategorias">
          <img id="filtericon" src="./assets/icons/menu-filter.png" alt="">
          <p id="tittlecategoria">Categoría</p>
        </button>
        
        <div class="barra-categorias">
          <button class="categoria" v-for="categoria in categorias" :key="categoria.id"
            @click="filtrarPorCategoria(categoria.id)">
            {{ categoria.nombre }}
          </button>
        </div>

      </div>




      <div ref="zonaHerramientas" class="herramientas">
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

      <footer></footer>
    </div>

  </div>

</template>



<script setup>
import { ref, computed, onMounted } from 'vue'
import { nextTick } from 'vue'

const herramientas = ref([])
const filtro = ref('')

const paginaActual = ref(1)
const herramientasPorPagina = 12
const zonaHerramientas = ref(null)

const mostrarCategorias = ref(false)

// Herramientas visibles por página
const herramientasPaginadas = computed(() => {
  const inicio = (paginaActual.value - 1) * herramientasPorPagina
  const fin = inicio + herramientasPorPagina
  return herramientas.value.slice(inicio, fin)
})

const totalPaginas = computed(() => {
  return Math.ceil(herramientas.value.length / herramientasPorPagina)
})

function siguientePagina() {
  if (paginaActual.value < totalPaginas.value) {
    paginaActual.value++
  }
}

function anteriorPagina() {
  if (paginaActual.value > 1) {
    paginaActual.value--
  }
}

// Cargar herramientas desde el backend
async function cargarHerramientas() {
  let url = 'http://localhost:8080/tools'

  if (filtro.value.trim() !== '') {
    url += `/buscar?nombre=${encodeURIComponent(filtro.value)}`
  }

  try {
    const res = await fetch(url)
    herramientas.value = await res.json()

    // Hacer scroll a la zona de herramientas si hay resultados
    if (herramientas.value.length && zonaHerramientas.value) {
      nextTick(() => {
        zonaHerramientas.value.scrollIntoView({ behavior: 'smooth' })
      })
    }

    // Reiniciar paginación a la primera página
    paginaActual.value = 1

  } catch (error) {
    console.error('Error al cargar herramientas:', error)
  }
}


// Cargar todas al iniciar
onMounted(() => {
  cargarHerramientas()
})

function toggleCategorias() {
  mostrarCategorias.value = !mostrarCategorias.value
}

const categorias = ref([])

async function cargarCategorias() {
  try {
    const res = await fetch('http://localhost:8080/api/categorias')
    categorias.value = await res.json()
  } catch (error) {
    console.error('Error al cargar categorías:', error)
  }
}

onMounted(() => {
  cargarCategorias()
})


</script>
