<template>
  <v-flex class="my-1 text-xs-left">
    <v-layout row>
      <v-flex xs3>
        <v-img :src="'https://lh6.googleusercontent.com/-l0_KVe2-FaE/AAAAAAAAAAI/AAAAAAAAcbo/_6Y8NSP3pOg/photo.jpg'" class="ml-2 my-2"></v-img>
      </v-flex>
      <v-flex xs9 class="pl-4">
          <v-layout column align-space-around>
            <v-flex class="mt-2">
              <span class="text-uppercase font-weight-bold mr-1">{{this.shop.network.name}}</span>
              <span style="font-size: 0.85em">({{this.distance}})</span>
            </v-flex>
            <v-flex>
              {{this.address}}
            </v-flex>
            <v-flex>
              {{this.shop.openTime}}-{{this.shop.closeTime}}
            </v-flex>
          </v-layout>
      </v-flex>
    </v-layout>
  </v-flex>
</template>

<script>
  import axios from 'axios'
  export default {
    data:() => ({
      address: null,
      distance: 150
    }),
    props: ['shop'],
    created() {
        axios({
          method: 'get',
          url: 'https://geocode-maps.yandex.ru/1.x/?apikey=' +
            'c05046c2-3599-474b-a74d-dac5c5e3e0d5' + '&format=json&geocode=' +
            this.shop.longitude + ',' + this.shop.latitude,
        }).then(response => {
          this.address = response.data.response.GeoObjectCollection.featureMember[0].GeoObject.name;
          console.log(response.data);
        }).catch(function (error) {
          console.log(error);
        }).finally(function () {
        });
    }
  }
</script>

<style scoped>
</style>

