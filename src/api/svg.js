const blockSize = 100;

function objectToSvg(floor) {
  const svg = document.createElementNS("http://www.w3.org/2000/svg", "svg");
  svg.setAttribute("width", "100%");
  svg.setAttribute("height", "100%");
  svg.setAttribute("viewBox", `0 0 ${blockSize * floor.width} ${blockSize * floor.height}`);
  floor.blocks.forEach(block => {
    // const svgBlock = document.createElementNS(`xlink:href=%back_url%/${block.blockType.toLowerCase()}.png"`, "image");
    const svgBlock = document.createElementNS("http://www.w3.org/2000/svg", "image");
    svgBlock.setAttributeNS('http://www.w3.org/1999/xlink', 'xlink:href', 'shelf.png');
    svgBlock.setAttribute("width", blockSize.toString());
    svgBlock.setAttribute("height", blockSize.toString());
    svgBlock.setAttribute("x", block.x * blockSize);
    svgBlock.setAttribute("y", block.y * blockSize);
    svgBlock.setAttribute("id", `shelf-${block.id}`);
    svgBlock.setAttribute("class", "shelf");
    svg.append(svgBlock);
  });
  return svg;
}
