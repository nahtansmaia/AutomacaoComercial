<template>
  <div class="DashBoardweather">
    <v-card class="mx-auto elevation-20" color="dark" style="width: 100%">
      <v-row class="temp">
        <v-col cols="8">
          <v-card-title ref="weather-title">
            <div>
              <div class="headline">
                <v-icon large>mdi-weather-partly-cloudy</v-icon>Teresópolis
              </div>
            </div>
          </v-card-title>
          <v-card-text ref="weather-body">
            <div>{{dateWeather}} - {{tempWeather}}º C</div>
            <div>Max: {{maxTemp}}º - Min: {{minTemp}}º - Umidade: {{humidity}}%</div>
          </v-card-text>
        </v-col>
      </v-row>
    </v-card>
  </div>
</template>

<script>
import axios from "axios";

export default {
  data: () => ({
    Info: {},
    dateWeather: new Date().toISOString().substr(0, 10),
    tempWeather: 0,
    maxTemp: 0,
    minTemp: 0,
    humidity: 0,
  }),
  async mounted() {
    const API_KEY = "36569a4439d78f419133ba7faf017a4c";
    await axios
      .get(
        "http://api.openweathermap.org/data/2.5/weather?id=3446606&appid=" +
          API_KEY
      )
      .then((resposta) => {
        this.Info = resposta.data;
        this.tempWeather = Math.round(this.Info.main.temp - 273.15);
        this.maxTemp = Math.round(this.Info.main.temp_max  - 273.15)
        this.minTemp = Math.round(this.Info.main.temp_min - 273.15)
        this.humidity = Math.round(this.Info.main.humidity)
      });
  },
};
</script>   

<style lang="scss" scoped>
.DashBoardweather {
  width: 30%;
  height: 50%;
  display: flex;
  border: 0px solid darkslategray;
  border-radius: 2%;
  margin: 20px;
  margin-top: 0%;
  
}
.temp {
  padding: 5%;
}
</style>