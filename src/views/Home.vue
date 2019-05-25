<template>
  <v-container fill-height fluid>
    <yandex-map
      id = "map"
      style="width: 100%; height: 100%"
      :coords="[54.62896654088406, 39.731893822753904]"
      zoom="10"
      :behaviors="['drag']"
    >

    </yandex-map>
  </v-container>
</template>

<script>

  import { yandexMap, ymapMarker } from 'vue-yandex-maps'

export default {
  name: 'home',
  components: { yandexMap, ymapMarker }
}
</script>

