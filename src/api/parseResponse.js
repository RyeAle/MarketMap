var blockTypeEnum = Object.freeze([
    'entry', 'quit', 'pass', 'shelf'
  ]
);

var jsonProp = [
  {
    latitude: '222111',
    longtitude: '333111',
    width: 6,
    height: 7,
    floor: 1,
    blocks:
      [
        {
          x: '2',
          y: '3',
          id: 1,
          type: 'entry'
        }
      ]
  }
];

parseResponse(jsonProp);

function parseResponse(json) {
  debugger
  json.forEach(value => {
    let { latitude, longtitude, width, height, floor, blocks } = value;
    console.log(latitude,longtitude,width,height,floor);
    blocks.forEach(value => {
      let { x, y, id, type } = value;
      console.log(x,y,id,type)
    });
  });
}

export default parseResponse;
