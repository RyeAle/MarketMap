<template>
  <v-container fill-height fluid>
    <yandex-map
      id = "map"
      style="width: 100%; height: 100%"
      :coords="[59.9682258, 30.321530599999996]"
      zoom="10"
      :behaviors="['drag']"
      :controls="['zoomControl']"
      :detailed-controls = dc

    >
      <ymap-marker v-for="shop in shops"
        :key="shop.id"
        marker-id=shop.id
        marker-type="placemark"
        :coords=coordsToArr(shop.lat,shop.lng)
        hint-content="Hint content 1"
        :balloon="{header: 'header', body: 'body', footer: 'footer'}"
        :icon="{color: 'green', glyph: 'cinema'}"
        cluster-name="1"
      ></ymap-marker>
    </yandex-map>
  </v-container>
</template>

<script>

  import { yandexMap, ymapMarker } from 'vue-yandex-maps'

export default {
  data:() => ({
    dc: {
      geolocationControl: {
        float: "none",
        position: {
          left: "90px"

        }
      },
      routeEditor: {
        float: "none",
        position: {
          left: "120px"

        }
      }
    },
    shops: [
      { id: 0,
        lat: 59.9682258,
        lng: 30.321530599999996
      },
      { id: 1,
        lat: 59.95,
        lng: 30.31
      },
    ]
  }),
  name: 'home',
  components: { yandexMap, ymapMarker },
  methods: {
    coordsToArr(lat, lng) {
      return [lat, lng]
    }
  }
}
</script>

