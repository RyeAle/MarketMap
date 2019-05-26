<template>
  <v-layout row justify-center>
    <img :src="'http://192.168.43.95:8080/'+this.imgUrl"  alt="">
    <h4>{{name}}</h4>
  </v-layout>
</template>
<script>
  export default {
    props: {
      imgUrl: String,
      name: String
    }
  }
</script>
<style scoped>
  /*div.root {*/
    /*display: flex;*/
    /*flex-direction: row;*/
    /*justify-content: space-between;*/
    /*background: #2a333a;*/
    /*padding: 0;*/
  /*}*/

  img {
    display: block;
  }
</style>
