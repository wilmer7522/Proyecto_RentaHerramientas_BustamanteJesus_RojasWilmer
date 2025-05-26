<template>
    <div class="tabla-filtrable">
        <h1 id="titulo-tabla">{{ titulo }}</h1>

        <div class="contenedor-tabla">
            <input v-model="filtroGeneral" type="text" :placeholder="placeholderFiltro" class="input-filtro" />

            <table class="tabla-datos" cellpadding="8" cellspacing="0">
                <thead>
                    <tr>
                        <th v-for="columna in columnas" :key="columna.campo">
                            {{ columna.titulo }}
                        </th>
                        <th v-if="mostrarAcciones">Acciones</th>
                    </tr>
                </thead>
                <tbody>
                    <tr v-for="item in datosFiltrados" :key="item.id">
                        <td v-for="columna in columnas" :key="`${item.id}-${columna.campo}`">
                            <input v-if="editando === item.id && columna.editable" v-model="item[columna.campo]" />
                            <span v-else>{{ item[columna.campo] }}</span>
                        </td>

                        <td v-if="mostrarAcciones" class="acciones">
                            <button v-if="mostrarEliminar" class="btn-accion btn-eliminar"
                                @click="emit('eliminar', item.id)">
                                <img src="../assets/icons/trash-x.png" alt="Eliminar">
                            </button>

                            <button v-if="editando !== item.id && mostrarEditar" class="btn-accion btn-editar"
                                @click="iniciarEdicion(item)">
                                <img src="../assets/icons/edit.png" alt="Editar">
                            </button>

                            <template v-else-if="mostrarEditar">
                                <button class="btn-accion btn-guardar" @click="emit('guardar', item)">
                                    <img src="../assets/icons/check.png" alt="Guardar">
                                </button>
                                <button class="btn-accion btn-cancelar" @click="cancelarEdicion(item.id)">
                                    <img src="../assets/icons/x.png" alt="Cancelar">
                                </button>
                            </template>
                        </td>
                    </tr>

                    <tr v-if="mostrarAgregar" class="fila-agregar">
                        <td v-for="columna in columnas" :key="`nuevo-${columna.campo}`">
                            <input v-model="nuevoItem[columna.campo]" :placeholder="columna.titulo" />
                        </td>
                        <td>
                            <button class="btn-accion btn-agregar" @click="emit('agregar', nuevoItem)">
                                <img src="../assets/icons/plus-circle.png" alt="Agregar">
                            </button>
                        </td>
                    </tr>
                </tbody>
            </table>
        </div>
    </div>
</template>

<script setup>
import { ref, computed, watch } from 'vue'

const props = defineProps({
    titulo: {
        type: String,
        required: true
    },
    datos: {
        type: Array,
        required: true
    },
    columnas: {
        type: Array,
        required: true,
        validator: (value) => {
            return value.every(col => 'campo' in col && 'titulo' in col)
        }
    },
    placeholderFiltro: {
        type: String,
        default: 'Buscar...'
    },
    camposFiltro: {
        type: Array,
        default: () => []
    },
    mostrarAcciones: {
        type: Boolean,
        default: true
    },
    mostrarAgregar: {
        type: Boolean,
        default: true
    },
    mostrarEditar: {
        type: Boolean,
        default: true
    },
    mostrarEliminar: {
        type: Boolean,
        default: true
    }
})

const emit = defineEmits(['agregar', 'editar', 'guardar', 'eliminar', 'cancelar'])

const filtroGeneral = ref('')
const editando = ref(null)
const itemOriginal = ref(null)
const nuevoItem = ref({})

// Inicializar nuevoItem con campos vacíos para cada columna
watch(() => props.columnas, (columnas) => {
    nuevoItem.value = columnas.reduce((obj, col) => {
        obj[col.campo] = ''
        return obj
    }, {})
}, { immediate: true })

const datosFiltrados = computed(() => {
    if (!filtroGeneral.value) return props.datos

    const termino = filtroGeneral.value.toLowerCase()
    return props.datos.filter(item => {
        // Si se especificaron campos para filtrar, usarlos
        const camposABuscar = props.camposFiltro.length > 0
            ? props.camposFiltro
            : props.columnas.map(col => col.campo)

        return camposABuscar.some(campo => {
            const valor = item[campo]
            if (typeof valor === 'string') {
                return valor.toLowerCase().includes(termino)
            } else if (typeof valor === 'number') {
                return valor.toString().includes(termino)
            }
            return false
        })
    })
})

const iniciarEdicion = (item) => {
    itemOriginal.value = JSON.parse(JSON.stringify(item)) // Copia profunda
    editando.value = item.id
    emit('editar', item)
}

const cancelarEdicion = (id) => {
    editando.value = null
    itemOriginal.value = null
    emit('cancelar', id)
}
</script>

<style scoped>
.tabla-filtrable {
    width: 100%;
    margin: 20px 0;
}

#titulo-tabla {
    margin-bottom: 20px;
    font-size: 24px;
    color: #333;
}

.contenedor-tabla {
    background: white;
    border-radius: 8px;
    padding: 20px;
    box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
}

.input-filtro {
    width: 100%;
    padding: 10px;
    margin-bottom: 15px;
    border: 1px solid #ddd;
    border-radius: 4px;
    font-size: 16px;
}

.tabla-datos {
    width: 100%;
    border-collapse: collapse;
}

.tabla-datos th {
    background-color: #f5f5f5;
    padding: 12px;
    text-align: left;
    font-weight: 600;
}

.tabla-datos td {
    padding: 12px;
    border-bottom: 1px solid #eee;
}

.tabla-datos tr:last-child td {
    border-bottom: none;
}

.tabla-datos input {
    width: 100%;
    padding: 8px;
    border: 1px solid #ddd;
    border-radius: 4px;
}

.acciones {
    display: flex;
    gap: 8px;
}

.btn-accion {
    border: none;
    background: none;
    cursor: pointer;
    padding: 5px;
    border-radius: 4px;
    transition: background-color 0.2s;
}

.btn-accion:hover {
    background-color: #f0f0f0;
}

.btn-accion img {
    width: 20px;
    height: 20px;
}

.fila-agregar td {
    background-color: #f9f9f9;
}

.fila-agregar input {
    width: 100%;
}
</style>