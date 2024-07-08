<script setup lang="ts">
// import VuePersianDatetimePicker from 'vue-persian-datetime-picker'
// import datePicker from '@alireza-ab/vue-persian-datepicker'
import datePicker, {PersianDate} from '@alireza-ab/vue-persian-datepicker'

const form = reactive({
  title: "",
  text: "",
  startDate: "",
  endDate: "",
  is_active: "",
  medias: []
})
const status = ref(true)
</script>

<template>
  <div>
    <form action="">
      <div class="input-group">
        <label for="title">تیتر</label>
        <input type="text" id="title">
      </div>
      <div class="input-group">
        <label for="text">متن</label>
        <input type="text" id="text">
      </div>
      <div class="input-group flex gap-2">
        <label for="status">وضعیت :</label>
        <div>
          <input type="checkbox" class="w-4 h-4" v-model="status" id="status">
          <span v-if="status"> فعال </span>
          <span v-else> غیر فعال </span>
        </div>
      </div>
      <div class="input-group">
        <label for="startTime">بازه زمانی نمایش :</label>
        <input type="datetime-local" id="startTime">
        <client-only>
          <date-picker></date-picker>
        </client-only>
      </div>
    </form>
  </div>
</template>

<style scoped lang="scss">
input:not([type="checkbox"]) {
  width: 100%;
  padding: 6px 12px;
  outline: 0;
  border-radius: 3px;

  &:focus {
    border: 2px solid rgba(0, 0, 255, 0.2);
    box-shadow: 0 0 3px rgba(0, 0, 255, 0.2);
  }
}
</style>
