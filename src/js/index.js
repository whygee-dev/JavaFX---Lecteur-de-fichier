var inverse = false;
var palindromique = false;
var diff = false;

const initialize = () => {
  document.querySelector("body").style.backgroundImage =
    "url('https://i.ibb.co/pRNx6rw/background.png')";

  changeEditorSize();
};

const changeEditorSize = () => {
  document.querySelector("#editor").style.height = height - 45 + "px";
  document.querySelector("#editor").style.width = width + "px";
};

const openAndReadFile = () => {
  const content = JFC.openTextFile(false, true).readRaw();
  editor.getSession().setValue(content);
};

const display = (_i, _p) => {
  const content = JFC.openTextFile(true, true).formatOutput(
    inverse,
    palindromique
  );

  if (content) {
    _p && document.querySelector("#check-palindromique").classList.toggle("hidden")
    _i && document.querySelector("#check-inverse").classList.toggle("hidden")
  }


  editor.getSession().setValue(content);
};

const toggleInverse = () => {
  inverse = !inverse;
  display(true, false);
};

const togglePalindromique = () => {
  palindromique = !palindromique;
  display(false, true);
};

const toggleDiff = () => {
  if (editor.getSession().getValue() === "") return;
  diff = !diff;

  if (diff) displayDiff();
  else removeDiff();
};

const displayDiff = () => {
  const value = editor.getSession().getValue();
  const toCompareWith = JFC.openTextFile(false, false).readRaw();

  document.querySelector("#editor").style.width = width / 2 + "px";

  document.querySelector("#diff").style.width = width / 2 + "px";
  document.querySelector("#diff").style.width = height - 40 + "px";

  value && toCompareWith && document.querySelector("#check-diff").classList.remove("hidden")
  document.querySelector("#diff").innerHTML = Diff.minDiff(
    value,
    toCompareWith
  );
};

const removeDiff = () => {
  document.querySelector("#editor").style.width = width + "px";

  document.querySelector("#diff").style.width = "0px";
  document.querySelector("#diff").style.width = "0px";
  document.querySelector("#check-palindromique").classList.add("hidden")
};
