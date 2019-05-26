<template>
  <div id="app">
    <v-app>
      <v-content fill class="pt-0">
        <router-view/>
      </v-content>
    </v-app>
  </div>
</template>
<script>

  export default {
    methods: {
      geoRequest() {
        // navigator.permissions.query({name: 'geolocation'}).then(function (result) {
        //   alert(result.state);
        // });
        navigator.geolocation.getCurrentPosition(
          function(position)
          {
            console.log("Доступ разрешён.");

            //действия с полученными данными
          },
          function(error)
          {
            // если ошибка (можно проверить код ошибки)
            if(error.PERMISSION_DENIED)
            {
              console.log("В доступе отказано!");
              alert("fsdfdsfsfd");
            }
          });
      }
    },
    mounted() {
      //this.geoRequest()
    }
  }
</script>
<style>
  #app {
    font-family: 'Avenir', Helvetica, Arial, sans-serif;
    -webkit-font-smoothing: antialiased;
    -moz-osx-font-smoothing: grayscale;
    text-align: center;
    color: #2c3e50;
  }
</style>
