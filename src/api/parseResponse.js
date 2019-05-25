function parseResponse(json) {
  json.forEach(value => {
    let { latitude, longtitude, width, height, floor, blocks } = value;
    blocks.forEach(value => {
      let { x, y, id, type } = value;
    });
  });
}
export default parseResponse;
