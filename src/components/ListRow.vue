<template>
    <v-container grid-list-md>
        <v-layout row>
            <v-flex xs8 offset-xs4 class="header">
                {{title}} <span style="font-size: 0.85em">{{distance}}</span> 
            </v-flex>
        </v-layout>
        <v-layout row>
            <v-flex xs8 offset-xs4 class="address">
                {{address}}
            </v-flex>
        </v-layout>
        <v-layout row>
            <v-flex xs8 offset-xs4 class="date">
                {{openTime}}
            </v-flex>
        </v-layout>
        <img :src="logoUrl" />
    </v-container>
</template>

<script>
    export default {
        props:['title', 'distance', 'address', 'openTime', 'logoUrl' ]
    }
</script>

<style scoped>
.header{
    margin-left: 71px !important;
    margin-top: 10px;
    font-weight: bold;
    font-size: 0.85em;
}

.address {
    margin-top: -10px;
    margin-left: 77px !important;
}

.date {
    margin-top: -10px;
    margin-left: 52px !important;
}

img{
    position: absolute;
    top: 3px;
    left: 30px;
    width: 22%;
}
</style>

