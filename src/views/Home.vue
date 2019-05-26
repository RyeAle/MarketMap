<template>
  <v-container fill-height fluid>
    <yandex-map
      id = "map"
      style="width: 100%; height: 100%"
      :coords="[59.9682258, 30.321530599999996]"
      zoom="10"
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
        :icon="{
                  layout: 'default#image',
                  imageHref:
                  'https://www.svgrepo.com/show/76519/dummy.svg',
                  imageSize: [43, 55],
                  imageOffset: [-22, -55]
                }"
        cluster-name="1"
      > </ymap-marker>
    </yandex-map>
    <toolbar style="z-index: 100; position: absolute; width: 90%;"/>
    <shop-list />
  </v-container>
</template>

<script>
  import Toolbar from '../components/Toolbar.vue';
  import ShopList from '../components/ShopList.vue';

  import { yandexMap, ymapMarker } from 'vue-yandex-maps'

export default {
  components: { yandexMap, ymapMarker, Toolbar, ShopList },
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
  methods: {
    coordsToArr(lat, lng) {
      return [lat, lng]
    }
  }
}
</script>

