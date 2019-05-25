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
    svgBlock.setAttribute("class", "shelf:");
    svg.append(svgBlock);
  });
  return svg;
}

// n - x, m - y
function createGrid(n, m) {
  const svg = document.createElementNS("http://www.w3.org/2000/svg", "svg");
  svg.setAttribute("width", "100%");
  svg.setAttribute("height", "100%");
  svg.setAttribute("viewBox", `0 0 ${blockSize * n} ${blockSize * m}`);
  for (let i = 0; i < n; i++) {
    for (let j = 0; j < m; j++) {
      const block = document.createElementNS("http://www.w3.org/2000/svg", "rect");
      block.setAttribute("style", "stroke:#000; fill:#FFF");
      block.setAttribute("width", blockSize.toString());
      block.setAttribute("height", blockSize.toString());
      block.setAttribute("x", j * blockSize);
      block.setAttribute("y", i * blockSize);
      block.setAttribute("onclick", "f(this)");
      block.setAttribute("id", `unmapped:${j}:${i}`);
      svg.append(block);
    }
  }
  return svg;
}

function f(obj) {
  obj.setAttribute("style", "stroke:#000; fill:#555");
}
