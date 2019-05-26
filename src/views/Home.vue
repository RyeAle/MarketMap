<template>
  <v-container fill-height fluid>
    <yandex-map
      id="map"
      style="width: 100%; height: 100%"
      :coords="[59.9682258, 30.321530599999996]"
      zoom="10"
      :controls="['zoomControl']"
      :detailed-controls=dc

    >
      <ymap-marker
        v-for="shop in shops"
        :key="shop.id"
        v-bind:marker-id=shop.id
        marker-type="placemark"
        :coords=coordsToArr(shop.latitude,shop.longitude)
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
      />
    </yandex-map>
    <toolbar style="z-index: 100; position: absolute; width: 90%;"/>
    <shop-list :shops="shops"/>
  </v-container>
</template>

<script>
  import Toolbar from '../components/Toolbar.vue';
  import ShopList from '../components/ShopList.vue';
  import axios from 'axios';

  import {yandexMap, ymapMarker} from 'vue-yandex-maps'

  export default {
    components: {yandexMap, ymapMarker, Toolbar, ShopList},
    data: () => ({
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
      shops: JSON.parse('[{"id":14,"longitude":30.33252,"latitude":59.937785,"openTime":"09.00","closeTime":"23.00","network":{"id":11,"name":"Пятерочка","logoUrl":"/img/network/1.png"}},{"id":15,"longitude":30.328132,"latitude":59.940773,"openTime":"09.00","closeTime":"22.00","network":{"id":12,"name":"Ашан","logoUrl":"/img/network/2.png"}},{"id":16,"longitude":30.334666,"latitude":59.942251,"openTime":"10.00","closeTime":"21.00","network":{"id":13,"name":"Перекресток","logoUrl":"/img/network/3.png"}},{"id":17,"longitude":30.335696,"latitude":59.93613,"openTime":"10.00","closeTime":"22.00","network":{"id":11,"name":"Пятерочка","logoUrl":"/img/network/1.png"}}]')
    }),
    name: 'home',
    methods: {
      coordsToArr(lat, lng) {
        return [lat, lng]
      }
    },
    beforeCreate() {
      axios({
        method: 'get',
        url: 'http://192.168.43.95:8080/shop',
        headers: {
          'Access-Control-Allow-Origin': '*'
        }
      }).then(response => {
        this.shops = response.data;
        console.log(response.data);
      }).catch(function (error) {
        console.log(error);
      }).finally(function () {
      });
    }
  }
</script>

