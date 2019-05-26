<template>
  <router-link v-bind:to="'/map?id=' + this.shop.id" tag="div">
    <v-flex class="my-1 text-xs-left">
      <v-layout row align-center>
        <v-flex xs2 >
          <v-img :src="'http://192.168.43.95:8080/' + this.shop.network.logoUrl"
                 class="ml-2 my-2"></v-img>
  <!--        'https://lh6.googleusercontent.com/-l0_KVe2-FaE/AAAAAAAAAAI/AAAAAAAAcbo/_6Y8NSP3pOg/photo.jpg'-->
        </v-flex>
        <v-flex xs8 class="pl-4">
            <v-layout column align-space-around>
              <v-flex class="mt-2">
                <span class="text-uppercase font-weight-bold mr-1">{{this.shop.network.name}}</span>
                <span style="font-size: 0.85em">({{this.distance}}м)</span>
              </v-flex>
              <v-divider />
              <v-flex>
                {{this.address}}
              </v-flex>
              <v-divider />
              <v-flex>
                {{this.shop.openTime}}-{{this.shop.closeTime}}
              </v-flex>
            </v-layout>
        </v-flex>
        <v-flex xs2 class="text-xs-center">
          <router-link v-bind:to="'/question?id=' + this.shop.id" tag="span">
            <v-icon>edit</v-icon>
           </router-link>
        </v-flex>
      </v-layout>
    </v-flex>
  </router-link>
</template>

<script>
  import axios from 'axios'
  import { getDistance } from 'geolib'
  export default {
    data:() => ({
      address: "qwerty",
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
        navigator.geolocation.getCurrentPosition(position => {
          this.distance = getDistance({
              latitude: this.shop.latitude, longitude: this.shop.longitude },
            { latitude: position.coords.latitude, longitude: position.coords.longitude }
          );
          console.log(this.distance)
        });

    }
  }
</script>

<style scoped>
</style>

