<template>
  <div class="container">
    <div class="main-screen">

      <div class="sidebar">
        <a href="https://proyecto-renta-herramientas-bustama-sage.vercel.app/admin"><img id="logo" src="/src/assets/header/LogoSinFondo.png" alt="Logo" /></a>

        <div class="menu-item" v-for="(menu, index) in menus" :key="index">
          <button class="menu-button" @click="menu.opciones ? toggleMenu(index) : seleccionarOpcion(menu.nombre)">
            <img :src="menu.icono" class="menu-icon" alt="icono de menú" />
            {{ menu.nombre }}
          </button>

          <transition name="fade-slide">
            <ul v-if="openIndex === index && menu.opciones" class="submenu">
              <li v-for="(opcion, i) in menu.opciones" :key="i" :class="{ activo: selectedOpcion === opcion }"
                @click="seleccionarOpcion(opcion)">
                {{ opcion }}
              </li>
            </ul>
          </transition>
        </div>

      </div>

      <div class="header">
        <button id="user"><img id="userimg" src="../assets/header/user.png" alt=""></button>

      </div>

    </div>

    <div v-if="selectedOpcion === 'Clientes'" class="clientes">
      <h1 id="tittleclient">Gestión de clientes</h1>

      <div class="cliente">

        <input v-model="filtroGeneral" type="text" placeholder="Buscar por cédula o nombre" class="input-filtro" />



        <table id="tablita" cellpadding="8" cellspacing="0">
          <thead>
            <tr>
              <th>Nombre</th>
              <th>Apellido</th>
              <th>Correo</th>
              <th>Teléfono</th>
              <th>Dirección</th>
              <th>Cédula</th>
              <th>Acciones</th>
            </tr>
          </thead>
          <tbody>
            <tr v-for="cliente in clientesPaginados" :key="cliente.id">
              <td>
                <input v-if="editando === cliente.id" v-model="cliente.nombre" />
                <span v-else>{{ cliente.nombre }}</span>
              </td>
              <td>
                <input v-if="editando === cliente.id" v-model="cliente.apellido" />
                <span v-else>{{ cliente.apellido }}</span>
              </td>
              <td>
                <input v-if="editando === cliente.id" v-model="cliente.correo" />
                <span v-else>{{ cliente.correo }}</span>
              </td>
              <td>
                <input v-if="editando === cliente.id" v-model="cliente.telefono" />
                <span v-else>{{ cliente.telefono }}</span>
              </td>
              <td>
                <input v-if="editando === cliente.id" v-model="cliente.direccion" />
                <span v-else>{{ cliente.direccion }}</span>
              </td>
              <td>
                <input v-if="editando === cliente.id" v-model="cliente.cedula" />
                <span v-else>{{ cliente.cedula }}</span>
              </td>
              <td id="acciones">
                <button id="delete" @click="eliminarCliente(cliente.id)"><img src="../assets/icons/trash-x.png"
                    alt=""></button>

                <button id="edit" v-if="editando !== cliente.id" @click="editarCliente(cliente.id)">
                  <img src="../assets/icons/edit.png" alt="">
                </button>

                <template v-else>
                  <button id="save" @click="guardarCambios(cliente)"><img src="../assets/icons/check.png"
                      alt=""></button>
                  <button id="cancel" @click="cancelarEdicion(cliente.id)"><img src="../assets/icons/x.png"
                      alt=""></button>
                </template>
              </td>

            </tr>

            <tr id="guardar">
              <td>
                <input v-model="nuevoCliente.nombre" placeholder="Nombre" />
                <span class="error" v-if="erroresCliente.nombre">{{ erroresCliente.nombre }}</span>
              </td>
              <td>
                <input v-model="nuevoCliente.apellido" placeholder="Apellido" />
                <span class="error" v-if="erroresCliente.apellido">{{ erroresCliente.apellido }}</span>
              </td>
              <td>
                <input v-model="nuevoCliente.correo" placeholder="Correo" />
                <span class="error" v-if="erroresCliente.correo">{{ erroresCliente.correo }}</span>
              </td>
              <td>
                <input v-model="nuevoCliente.telefono" placeholder="Teléfono" />
                <span class="error" v-if="erroresCliente.telefono">{{ erroresCliente.telefono }}</span>
              </td>
              <td>
                <input v-model="nuevoCliente.direccion" placeholder="Dirección" />
                <span class="error" v-if="erroresCliente.direccion">{{ erroresCliente.direccion }}</span>
              </td>
              <td>
                <input v-model="nuevoCliente.cedula" placeholder="Cédula" />
                <span class="error" v-if="erroresCliente.cedula">{{ erroresCliente.cedula }}</span>
              </td>
              <td>
                <!-- Botón “guardar cliente” -->
                <button id="buttonsave" @click="agregarCliente" :disabled="formularioInvalido" :class="[
                  'rounded-md transition flex items-center justify-center',
                  formularioInvalido
                    ? 'bg-gray-400 cursor-not-allowed opacity-50'
                    : 'bg-blue-600 hover:bg-blue-700 cursor-pointer'
                ]">
                  <img id="imgsave" src="../assets/icons/plus-circle.png" alt="Guardar" class="w-6 h-6" />
                </button>

              </td>
            </tr>

          </tbody>
        </table>

        <div class="mt-3 d-flex justify-content-center gap-2">
          <button id="atras" class="btn btn-sm btn-secondary" :disabled="paginaActualClientes === 1"
            @click="paginaActualClientes--">
            Anterior
          </button>



          <span id="paginationcliente" class="px-3 align-self-center">
            Página {{ paginaActualClientes }} de {{ Math.ceil(clientesFiltrados.length / elementosPorPaginaClientes) }}
          </span>

          <button id="adelante" class="btn btn-sm btn-secondary"
            :disabled="paginaActualClientes >= Math.ceil(clientesFiltrados.length / elementosPorPaginaClientes)"
            @click="paginaActualClientes++">
            Siguiente
          </button>
        </div>


      </div>
    </div>


    <div v-if="selectedOpcion === 'Proveedores'" class="proveedores">

      <h1 id="tittleclient">Gestión de proveedores</h1>

      <div class="proveedor">
        <input v-model="filtroProveedor" type="text" placeholder="Buscar por nombre o NIT" class="input-filtro" />

        <table id="tablita" cellpadding="8" cellspacing="0">
          <thead>
            <tr>
              <th>Nombre</th>
              <th>Teléfono</th>
              <th>Dirección</th>
              <th>NIT</th>
              <th>Correo</th>
              <th>Acciones</th>
            </tr>
          </thead>

          <tbody>
            <tr v-for="proveedor in proveedoresFiltradosPaginados" :key="proveedor.id">
              <td>
                <input v-if="editandoProveedor === proveedor.id" v-model="proveedor.nombre" />
                <span v-else>{{ proveedor.nombre }}</span>
              </td>
              <td>
                <input v-if="editandoProveedor === proveedor.id" v-model="proveedor.telefono" />
                <span v-else>{{ proveedor.telefono }}</span>
              </td>
              <td>
                <input v-if="editandoProveedor === proveedor.id" v-model="proveedor.direccion" />
                <span v-else>{{ proveedor.direccion }}</span>
              </td>
              <td>
                <input v-if="editandoProveedor === proveedor.id" v-model="proveedor.nit" />
                <span v-else>{{ proveedor.nit }}</span>
              </td>
              <td>
                <input v-if="editandoProveedor === proveedor.id" v-model="proveedor.correo" />
                <span v-else>{{ proveedor.correo }}</span>
              </td>
              <td id="acciones">
                <button id="delete" @click="eliminarProveedor(proveedor.id)">
                  <img src="../assets/icons/trash-x.png" alt="">
                </button>

                <button id="edit" v-if="editandoProveedor !== proveedor.id" @click="editarProveedor(proveedor.id)">
                  <img src="../assets/icons/edit.png" alt="">
                </button>

                <template v-else>
                  <button id="save" @click="guardarCambiosProveedor(proveedor)">
                    <img src="../assets/icons/check.png" alt="">
                  </button>
                  <button id="cancel" @click="cancelarEdicionProveedor(proveedor.id)">
                    <img src="../assets/icons/x.png" alt="">
                  </button>
                </template>
              </td>
            </tr>

            <tr id="guardar">
              <td>
                <input v-model="nuevoProveedor.nombre" placeholder="Nombre" />
                <span v-if="erroresProveedor.nombre" class="error">{{ erroresProveedor.nombre }}</span>
              </td>
              <td>
                <input v-model="nuevoProveedor.telefono" placeholder="Teléfono" />
                <span v-if="erroresProveedor.telefono" class="error">{{ erroresProveedor.telefono }}</span>
              </td>
              <td>
                <input v-model="nuevoProveedor.direccion" placeholder="Dirección" />
                <span v-if="erroresProveedor.direccion" class="error">{{ erroresProveedor.direccion }}</span>
              </td>
              <td>
                <input v-model="nuevoProveedor.nit" placeholder="NIT" />
                <span v-if="erroresProveedor.nit" class="error">{{ erroresProveedor.nit }}</span>
              </td>
              <td>
                <input v-model="nuevoProveedor.correo" placeholder="Correo" />
                <span v-if="erroresProveedor.correo" class="error">{{ erroresProveedor.correo }}</span>
              </td>
              <td>
                <button id="buttonsave" @click="agregarProveedor" :disabled="formularioInvalidoProveedor" :class="[
                  'rounded-md transition flex items-center justify-center',
                  formularioInvalidoProveedor
                    ? 'bg-gray-400 cursor-not-allowed opacity-50'
                    : 'bg-blue-600 hover:bg-blue-700 cursor-pointer'
                ]">
                  <img id="imgsave" src="../assets/icons/plus-circle.png" alt="">
                </button>
              </td>
            </tr>
          </tbody>
        </table>

        <div class="mt-3 d-flex justify-content-center gap-2">
          <button id="atras" class="btn btn-sm btn-secondary" :disabled="paginaProveedor === 1"
            @click="paginaProveedor--">
            Anterior
          </button>

          <span id="paginationProveedor" class="px-3 align-self-center">
            Página {{ paginaProveedor }} de {{ Math.ceil(proveedoresFiltrados.length / proveedoresPorPagina) }}
          </span>

          <button id="adelante" class="btn btn-sm btn-secondary"
            :disabled="paginaProveedor >= Math.ceil(proveedoresFiltrados.length / proveedoresPorPagina)"
            @click="paginaProveedor++">
            Siguiente
          </button>
        </div>


      </div>
    </div>

    <div v-if="selectedOpcion === 'Historial del alquileres'" class="alquileres">

      <h1 id="tittleclient">Gestión de Alquileres</h1>



      <div class="alquiler">
        <input v-model="filtroAlquileres" type="text" placeholder="Buscar por ID" class="input-filtro" />


        <table id="tablita1" class="tabla-oscura">
          <thead class="">
            <tr>
              <th>ID</th>
              <th>Fecha Alquiler</th>
              <th>Fecha Devolución</th>
              <th>Estado</th>
              <th>Detalles del Alquiler</th>
            </tr>
          </thead>
          <tbody>
            <tr v-for="alquiler in alquileresPaginados" :key="alquiler.id">
              <td>{{ alquiler.id }}</td>
              <td>{{ formatFecha(alquiler.fechaAlquiler) }}</td>
              <td>{{ formatFecha(alquiler.fechaDevolucion) }}</td>
              <td :class="'estado-' + alquiler.estadoAlquiler.toLowerCase()">
                {{ alquiler.estadoAlquiler }}
              </td>
              <td>
                <table id="tabla2" class="table table-sm table-bordered mb-0">
                  <thead class="thead-dark">
                    <tr>
                      <th>ID</th>
                      <th>Herramienta</th>
                      <th>Cantidad</th>
                      <th>Días</th>
                      <th>Precio Unitario</th>
                      <th>Subtotal</th>
                    </tr>
                  </thead>
                  <tbody>
                    <tr v-for="detalle in alquiler.detalles" :key="detalle.id">
                      <td>{{ detalle.id }}</td>
                      <td>{{ detalle.nombreHerramienta }}</td>
                      <td>{{ detalle.cantidad }}</td>
                      <td>{{ detalle.cantidadDias }}</td>
                      <td>${{ detalle.precioUnitario.toLocaleString() }}</td>
                      <td>${{ detalle.subtotal.toLocaleString() }}</td>
                    </tr>
                  </tbody>
                </table>
              </td>
            </tr>
          </tbody>
        </table>

        <div class="mt-3 d-flex justify-content-center gap-2">
          <button id="atras" class="btn btn-sm btn-secondary" :disabled="paginaActual === 1" @click="paginaActual--">
            Anterior
          </button>

          <span id="paginationAlquiler" class="px-3 align-self-center">
            Página {{ paginaActual }} de {{ Math.ceil(alquileresFiltrados.length / elementosPorPagina) }}
          </span>

          <button id="adelante" class="btn btn-sm btn-secondary"
            :disabled="paginaActual >= Math.ceil(alquileresFiltrados.length / elementosPorPagina)"
            @click="paginaActual++">
            Siguiente
          </button>
        </div>


      </div>

    </div>

    <div v-if="selectedOpcion === 'Reporte de daños'" class="reportes-dano">
      <h1 id="tittleclient">Reportes de Daño</h1>

      <div class="reporte-dano">
        <table id="tablita" cellpadding="8" cellspacing="0">
          <thead>
            <tr>
              <th>ID</th>
              <th>Descripción</th>
              <th>Fecha Reporte</th>
              <th>Herramienta ID</th>
              <th>Herramienta</th>
            </tr>
          </thead>
          <tbody>
            <tr v-for="reporte in reportesPaginados" :key="reporte.id">
              <td>{{ reporte.id }}</td>
              <td>{{ reporte.descripcion }}</td>
              <td>{{ formateFecha(reporte.fechaReporte) }}</td>
              <td>{{ reporte.herramientaId }}</td>
              <td>{{ reporte.herramientaNombre }}</td>
            </tr>
          </tbody>
        </table>

        <div class="mt-3 d-flex justify-content-center gap-2">
          <button id="atras" class="btn btn-sm btn-secondary" :disabled="paginaReporte === 1" @click="paginaReporte--">
            Anterior
          </button>

          <span id="paginationDano" class="px-3 align-self-center">
            Página {{ paginaReporte }} de {{ totalPaginasReporte }}
          </span>

          <button id="adelante" class="btn btn-sm btn-secondary" :disabled="paginaReporte === totalPaginasReporte"
            @click="paginaReporte++">
            Siguiente
          </button>
        </div>

      </div>
    </div>

    <div v-if="selectedOpcion === 'Monitoreo de pagos y facturacion'" class="reportes-dano">
      <h1 id="tittleclient">Reportes de pagos y facturación</h1>

      <div class="pagos">

        <input v-model="filtroPago" type="text" placeholder="Buscar por ID o Estado de Pago" class="input-filtro" />

        <table id="tablita" cellpadding="8" cellspacing="0">
          <thead>
            <tr>
              <th>ID</th>
              <th>Monto</th>
              <th>Fecha de pago</th>
              <th>Estado de pago</th>
              <th>Método de pago</th>
              <th>Alquiler ID</th>
            </tr>
          </thead>
          <tbody>
            <tr v-for="pago in pagosPaginados" :key="pago.id">
              <td>{{ pago.id }}</td>
              <td>{{ pago.monto }}</td>
              <td>{{ formateFechapago(pago.fechaPago) }}</td>
              <td>{{ pago.estadoPago }}</td>
              <td>{{ pago.metodoPago }}</td>
              <td>{{ pago.alquilerId }}</td>
            </tr>
          </tbody>
        </table>

        <div class="mt-3 d-flex justify-content-center gap-2">
          <button id="atras" class="btn btn-sm btn-secondary" :disabled="paginaPago === 1" @click="paginaPago--">
            Anterior
          </button>

          <span id="paginationPago" class="px-3 align-self-center">
            Página {{ paginaPago }} de {{ totalPaginasPago }}
          </span>

          <button id="adelante" class="btn btn-sm btn-secondary" :disabled="paginaPago === totalPaginasPago"
            @click="paginaPago++">
            Siguiente
          </button>
        </div>


      </div>
    </div>
  </div>
</template>

<script setup>
// ===============================
// 📦 Imports
// ===============================
import '../admin.css'
import { ref, onMounted, computed, watch } from 'vue'
import axios from 'axios'
import Swal from 'sweetalert2'

// 🖼️ Íconos
import home from '../assets/icons/home.png'
import gestion from '../assets/icons/archive.png'
import control from '../assets/icons/history.png'
import supervision from '../assets/icons/wallet.png'
import reporte from '../assets/icons/chart-line.png'

// main.js o main.ts
import 'bootstrap/dist/css/bootstrap.min.css'
import 'bootstrap/dist/js/bootstrap.bundle.min.js'

const apiUrl = "https://rentaherramientas-bustamantejesus.onrender.com"

// ===============================
// 🔧 Estados globales de interfaz
// ===============================
const openIndex = ref(null)               // Menú desplegado actualmente
const selectedOpcion = ref(null)          // Opción de submenú seleccionada

// ===============================
// 📋 Definición del menú lateral
// ===============================
const menus = ref([
  { nombre: 'Home', icono: home, opciones: null },
  { nombre: 'Gestión', icono: gestion, opciones: ['Clientes', 'Proveedores'] },
  { nombre: 'Control', icono: control, opciones: ['Historial del alquileres', 'Reporte de daños'] },
  { nombre: 'Supervisión', icono: supervision, opciones: ['Monitoreo de pagos y facturacion'] },
  { nombre: 'Reportes', icono: reporte, opciones: ['Estadísticas de ingresos', 'Equipos más alquilados', 'Rentabilidad'] }
])

// ===============================
// 🧠 Funciones del menú lateral
// ===============================
function toggleMenu(index) {
  openIndex.value = openIndex.value === index ? null : index
}

function seleccionarOpcion(opcion) {
  selectedOpcion.value = opcion
}

// ===============================
// 👥 Gestión de clientes
// ===============================
const clientes = ref([])

// Obtener todos los clientes
const obtenerClientes = async () => {
  try {
    const response = await fetch(`${apiUrl}/auth/client`, {
      headers: {
        'Content-Type': 'application/json'
      }
    })

    if (!response.ok) {
      throw new Error(`Error HTTP: ${response.status}`)
    }

    clientes.value = await response.json()
  } catch (error) {
    console.error('Error al obtener clientes:', error)
  }
}

onMounted(() => {
  obtenerClientes()
})

// ===============================
// 🗑️ Eliminar cliente con confirmación
// ===============================
const eliminarCliente = async (id) => {
  try {
    const result = await Swal.fire({
      title: '¿Eliminar cliente?',
      text: 'Esta acción no se puede deshacer.',
      icon: 'warning',
      showCancelButton: true,
      confirmButtonText: 'Sí, eliminar',
      cancelButtonText: 'Cancelar',
      customClass: {
        popup: 'mi-popup',
        title: 'mi-titulo',
        confirmButton: 'mi-boton-confirmar',
        cancelButton: 'mi-boton-cancelar'
      },
      buttonsStyling: false
    })

    if (result.isConfirmed) {
      await axios.delete(`${process.env.VITE_API_URL}/auth/client/${id}`)
      await obtenerClientes()

      Swal.fire({
        title: '¡Eliminado!',
        text: 'El cliente fue eliminado correctamente.',
        icon: 'success',
        customClass: { popup: 'mi-popup' }
      })
    }
  } catch (error) {
    let mensaje = 'Ocurrió un error al eliminar el cliente.'

    if (error.response && error.response.data?.includes('alquiler')) {
      mensaje = 'Este cliente tiene registros de alquiler activos. Elimínelos primero.'
    }

    Swal.fire({
      title: 'Error',
      text: mensaje,
      icon: 'error',
      customClass: { popup: 'mi-popup' }
    })
  }
}

// ===============================
// ✏️ Editar cliente
// ===============================
const editando = ref(null)
const clienteOriginal = ref(null)

// Activar modo edición
const editarCliente = (id) => {
  const cliente = clientes.value.find(c => c.id === id)
  clienteOriginal.value = { ...cliente }
  editando.value = id
}

// Guardar cambios del cliente editado
const guardarCambios = async (cliente) => {
  try {
    await axios.patch(`${apiUrl}/auth/client/${cliente.id}`, cliente)
    editando.value = null
    await obtenerClientes()

    Swal.fire({
      title: 'Cliente actualizado',
      text: 'Los datos se guardaron correctamente.',
      icon: 'success',
      customClass: { popup: 'mi-popup' }
    })
  } catch (error) {
    console.error('Error al guardar cambios:', error)
    Swal.fire({
      title: 'Error',
      text: 'No se pudo guardar los cambios.',
      icon: 'error'
    })
  }
}

// Cancelar edición y restaurar datos originales
const cancelarEdicion = (id) => {
  const index = clientes.value.findIndex(c => c.id === id)
  if (index !== -1 && clienteOriginal.value) {
    clientes.value[index] = { ...clienteOriginal.value }
  }
  editando.value = null
  clienteOriginal.value = null
}

// ===============================
// ➕ Agregar nuevo cliente
// ===============================

const nuevoCliente = ref({
  nombre: '',
  apellido: '',
  correo: '',
  telefono: '',
  direccion: '',
  cedula: ''
})

const erroresCliente = ref({
  nombre: '',
  apellido: '',
  correo: '',
  telefono: '',
  direccion: '',
  cedula: ''
})

// 🔒 Computado que determina si el formulario es inválido
const formularioInvalido = computed(() => {
  const camposVacios = Object.values(nuevoCliente.value).some(valor => !valor)
  const hayErrores = Object.values(erroresCliente.value).some(error => error)
  return camposVacios || hayErrores
})

// Validación en tiempo real
watch(nuevoCliente, (nuevo) => {
  erroresCliente.value.nombre = !nuevo.nombre
    ? 'El nombre es obligatorio'
    : nuevo.nombre.length > 50
      ? 'Máximo 50 caracteres'
      : ''

  erroresCliente.value.apellido = !nuevo.apellido
    ? 'El apellido es obligatorio'
    : nuevo.apellido.length > 50
      ? 'Máximo 50 caracteres'
      : ''

  erroresCliente.value.correo = !nuevo.correo
    ? 'El correo es obligatorio'
    : !/^[^\s@]+@[^\s@]+\.[^\s@]+$/.test(nuevo.correo)
      ? 'Formato de correo inválido'
      : ''

  erroresCliente.value.telefono = !nuevo.telefono
    ? 'El teléfono es obligatorio'
    : !/^\d{7,15}$/.test(nuevo.telefono)
      ? 'Debe contener entre 7 y 15 dígitos'
      : ''

  erroresCliente.value.direccion = !nuevo.direccion
    ? 'La dirección es obligatoria'
    : nuevo.direccion.length > 100
      ? 'Máximo 100 caracteres'
      : ''

  erroresCliente.value.cedula = !nuevo.cedula
    ? 'La cédula es obligatoria'
    : !/^\d{5,15}$/.test(nuevo.cedula)
      ? 'Debe tener entre 5 y 15 dígitos numéricos'
      : ''
}, { deep: true })

// Envío del formulario
const agregarCliente = async () => {
  const datos = nuevoCliente.value

  const hayErrores = Object.values(erroresCliente.value).some(msg => msg)

  if (hayErrores) {
    alert('Corrige los errores antes de enviar el formulario.')
    return
  }

  try {
    await axios.post(`${import.meta.env.VITE_API_URL}/auth/client`, {
      nombre: datos.nombre,
      apellido: datos.apellido,
      correo: datos.correo,
      telefono: datos.telefono,
      direccion: datos.direccion,
      cedula: datos.cedula,
      password: 'default123',
      idRol: 3
    })

    // Limpiar formulario
    nuevoCliente.value = {
      nombre: '',
      apellido: '',
      correo: '',
      telefono: '',
      direccion: '',
      cedula: ''
    }

    obtenerClientes()
  } catch (error) {
    alert('Error al agregar cliente: ' + (error.response?.data?.message || error.message))
  }
}



// ===============================
// 🔍 Filtro de búsqueda de clientes
// ===============================
const filtroGeneral = ref('')

const clientesFiltrados = computed(() => {
  const termino = filtroGeneral.value.toLowerCase()
  return clientes.value.filter(cliente =>
    cliente.cedula?.toString().includes(termino) ||
    cliente.nombre?.toLowerCase().includes(termino)
  )
})

const paginaActualClientes = ref(1);
const elementosPorPaginaClientes = 5;

const clientesPaginados = computed(() => {
  const inicio = (paginaActualClientes.value - 1) * elementosPorPaginaClientes;
  const fin = inicio + elementosPorPaginaClientes;
  return clientesFiltrados.value.slice(inicio, fin);
});


const proveedores = ref([])
const filtroProveedor = ref('')

const editandoProveedor = ref(null)
const proveedorOriginal = ref(null)

const obtenerProveedores = async () => {
  try {
    const response = await axios.get(`/auth/suppliers`)
    console.log('Alquileres:', response.data)
    proveedores.value = response.data
  } catch (error) {
    console.error('Error al obtener alquileres:', error)
  }
}

const nuevoProveedor = ref({
  nombre: '',
  telefono: '',
  direccion: '',
  nit: '',
  correo: ''
})

const erroresProveedor = ref({
  nombre: '',
  telefono: '',
  direccion: '',
  nit: '',
  correo: ''
})

// 🔒 Computado que determina si el formulario es inválido
const formularioInvalidoProveedor = computed(() => {
  const camposVacios = Object.values(nuevoProveedor.value).some(valor => !valor)
  const hayErrores = Object.values(erroresProveedor.value).some(error => error)
  return camposVacios || hayErrores
})

// Validación en tiempo real
watch(nuevoProveedor, (nuevo) => {
  erroresProveedor.value.nombre = !nuevo.nombre
    ? 'El nombre es obligatorio'
    : nuevo.nombre.length > 50
      ? 'Máximo 50 caracteres'
      : ''

  erroresProveedor.value.telefono = !nuevo.telefono
    ? 'El teléfono es obligatorio'
    : !/^\d{7,15}$/.test(nuevo.telefono)
      ? 'Debe contener entre 7 y 15 dígitos'
      : ''

  erroresProveedor.value.direccion = !nuevo.direccion
    ? 'La dirección es obligatoria'
    : nuevo.direccion.length > 100
      ? 'Máximo 100 caracteres'
      : ''

  erroresProveedor.value.nit = !nuevo.nit
    ? 'El NIT es obligatorio'
    : !/^\d{5,15}$/.test(nuevo.nit)
      ? 'Debe tener entre 5 y 15 dígitos numéricos'
      : ''

  erroresProveedor.value.correo = !nuevo.correo
    ? 'El correo es obligatorio'
    : !/^[^\s@]+@[^\s@]+\.[^\s@]+$/.test(nuevo.correo)
      ? 'Formato de correo inválido'
      : ''
}, { deep: true })

// Envío del formulario
const agregarProveedor = async () => {
  const datos = nuevoProveedor.value

  const hayErrores = Object.values(erroresProveedor.value).some(msg => msg)

  if (hayErrores) {
    alert('Corrige los errores antes de enviar el formulario.')
    return
  }

  try {
    await axios.post(`${import.meta.env.VITE_API_URL}/auth/suppliers`, {
      nombre: datos.nombre,
      telefono: datos.telefono,
      direccion: datos.direccion,
      nit: datos.nit,
      correo: datos.correo,
      password: 'default123',
      idRol: 2
    })

    nuevoProveedor.value = {
      nombre: '',
      telefono: '',
      direccion: '',
      nit: '',
      correo: ''
    }

    obtenerProveedores()
  } catch (error) {
    if (error.response?.status === 400 && error.response.data) {
      // Si el backend devuelve errores específicos, los mostramos
      erroresProveedor.value = error.response.data
    } else {
      alert('Error al agregar proveedor: ' + (error.response?.data?.message || error.message))
    }
  }
}



const eliminarProveedor = async (id) => {
  try {
    const result = await Swal.fire({
      title: '¿Eliminar proveedor?',
      text: 'Esta acción no se puede deshacer.',
      icon: 'warning',
      showCancelButton: true,
      confirmButtonText: 'Sí, eliminar',
      cancelButtonText: 'Cancelar'
    })

    if (result.isConfirmed) {
      await axios.delete(`${process.env.VITE_API_URL}/auth/suppliers/${id}`)
      obtenerProveedores()
      Swal.fire('¡Eliminado!', 'El proveedor fue eliminado correctamente.', 'success')
    }
  } catch (error) {
    Swal.fire('Error', 'No se pudo eliminar el proveedor.', 'error')
  }
}

const editarProveedor = (id) => {
  const proveedor = proveedores.value.find(p => p.id === id)
  proveedorOriginal.value = { ...proveedor }
  editandoProveedor.value = id
}

const cancelarEdicionProveedor = (id) => {
  const index = proveedores.value.findIndex(p => p.id === id)
  if (index !== -1 && proveedorOriginal.value) {
    proveedores.value[index] = { ...proveedorOriginal.value }
  }
  editandoProveedor.value = null
  proveedorOriginal.value = null
}

const guardarCambiosProveedor = async (proveedor) => {
  // Construye el objeto que espera el backend
  const proveedorUpdateRequest = {
    nombre: proveedor.nombre,
    telefono: proveedor.telefono,
    direccion: proveedor.direccion,
    nit: proveedor.nit,
    correo: proveedor.correo
  }

  try {
    console.log('Proveedor enviado:', proveedor)
    await axios.patch(`${process.env.VITE_API_URL}/auth/suppliers/${proveedor.id}`, proveedorUpdateRequest)
    editandoProveedor.value = null
    obtenerProveedores()
    Swal.fire('Proveedor actualizado', 'Los datos se guardaron correctamente.', 'success')
  } catch (error) {
    console.error('Error al guardar cambios:', error.response?.data || error)
    Swal.fire('Error', 'No se pudo guardar los cambios.', 'error')
  }
}



const proveedoresFiltrados = computed(() => {
  return proveedores.value.filter(proveedor => {
    const termino = filtroProveedor.value.toLowerCase()
    return (
      proveedor.nombre?.toLowerCase().includes(termino) ||
      proveedor.nit?.toLowerCase().includes(termino)
    )
  })
})




const paginaProveedor = ref(1)
const proveedoresPorPagina = 5

const proveedoresFiltradosPaginados = computed(() => {
  const termino = filtroProveedor.value.toLowerCase()
  const filtrados = proveedoresFiltrados.value // Ya contiene el filtro por nombre o NIT
  const inicio = (paginaProveedor.value - 1) * proveedoresPorPagina
  const fin = inicio + proveedoresPorPagina
  return filtrados.slice(inicio, fin)
})


const alquileres = ref([])
const filtroAlquileres = ref('')

// Obtener todos los alquileres
const obtenerAlquileres = async () => {
  try {
    const response = await axios.get(`${apiUrl}/service/alquileres`)
    console.log('Alquileres:', response.data)
    alquileres.value = response.data
  } catch (error) {
    console.error('Error al obtener alquileres:', error)
  }
}

// Calcula el máximo de herramientas para dibujar los headers
const maxDetalles = computed(() =>
  Math.max(...alquileres.value.map(a => a.detalles?.length || 0))
)

// Filtrar alquileres
const alquileresFiltrados = computed(() => {
  const termino = filtroAlquileres.value.toLowerCase()
  return alquileres.value.filter(alquiler =>
    alquiler.id?.toString().includes(termino) ||
    alquiler.fecha_alquiler?.toLowerCase().includes(termino) ||
    alquiler.fecha_devolucion?.toLowerCase().includes(termino) ||
    alquiler.estadoAlquiler?.toLowerCase().includes(termino)
  )
})

// Formatear fecha
const formatFecha = (fecha) => {
  if (!fecha) return 'N/A'
  return new Date(fecha).toLocaleDateString('es-ES')
}

// Cargar alquileres al montar el componente
onMounted(() => {
  obtenerAlquileres()
})

const paginaActual = ref(1)
const elementosPorPagina = 3

const alquileresPaginados = computed(() => {
  const inicio = (paginaActual.value - 1) * elementosPorPagina
  const fin = inicio + elementosPorPagina
  return alquileresFiltrados.value.slice(inicio, fin)
})

const reportesDano = ref([]);

const paginaReporte = ref(1);
const reportesPorPagina = 10;

const pagosFiltrados = computed(() => {
  return pagos.value.filter((p) => {
    const termino = filtroPago.value.trim().toLowerCase();
    return (
      p.id.toString().includes(termino) ||
      p.estadoPago.toLowerCase().includes(termino)
    );
  });
});

const reportesPaginados = computed(() => {
  const inicio = (paginaReporte.value - 1) * reportesPorPagina;
  const fin = inicio + reportesPorPagina;
  return reportesDano.value.slice(inicio, fin);
});

const totalPaginasReporte = computed(() => {
  return Math.ceil(reportesDano.value.length / reportesPorPagina);
});

const obtenerReportesDano = async () => {
  try {
    const response = await fetch(`${process.env.VITE_API_URL}/service/reportes-dano`);
    if (!response.ok) {
      throw new Error("Error al obtener reportes de daño");
    }
    const data = await response.json();
    reportesDano.value = data;
  } catch (error) {
    console.error(error);
  }
};

const formateFecha = (fecha) => {
  const opciones = { year: 'numeric', month: '2-digit', day: '2-digit' };
  return new Date(fecha).toLocaleDateString('es-ES', opciones);
};

onMounted(() => {
  obtenerReportesDano()
})

const pagos = ref([]);

const paginaPago = ref(1);
const pagosPorPagina = 10;

const pagosPaginados = computed(() => {
  const inicio = (paginaPago.value - 1) * pagosPorPagina;
  const fin = inicio + pagosPorPagina;
  return pagos.value.slice(inicio, fin);
});

const totalPaginasPago = computed(() => {
  return Math.ceil(pagos.value.length / pagosPorPagina);
});

const obtenerPagos = async () => {
  try {
    const response = await fetch(`${apiUrl}/service/pago`);
    if (!response.ok) {
      throw new Error("Error al obtener los pagos");
    }
    const data = await response.json();
    pagos.value = data;
  } catch (error) {
    console.error(error);
  }
};

const formateFechapago = (fecha) => {
  const opciones = { year: 'numeric', month: '2-digit', day: '2-digit' };
  return new Date(fecha).toLocaleDateString('es-ES', opciones);
};

onMounted(() => {
  obtenerPagos()
})


</script>