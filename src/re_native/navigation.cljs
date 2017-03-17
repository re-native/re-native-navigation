(ns re-native.navigation
  (:require [reagent.core :as r]
            [re-frame.core :as re]))

(def react-navigation (js/require "react-navigation"))

; Core
(def createNavigationContainer (.-createNavigationContainer react-navigation))
(def StateUtils                (.-StateUtils react-navigation))
(def PropTypes                 (.-PropTypes react-navigation))
(def addNavigationHelpers      (.-addNavigationHelpers react-navigation))
(def NavigationActions         (.-NavigationActions react-navigation))

; Navigators
(def createNavigator (.-createNavigator react-navigation))
(def StackNavigator  (.-StackNavigator react-navigation))
(def TabNavigator    (.-TabNavigator react-navigation))
(def DrawerNavigator (.-DrawerNavigator react-navigation))

; Routers
(def StackRouter (.-StackRouter react-navigation))
(def TabRouter   (.-TabRouter react-navigation))

; Views
(def Transitioner (.-Transitioner react-navigation))
(def CardStack    (.-CardStack react-navigation))
(def DrawerView   (.-DrawerView react-navigation))
(def TabView      (.-TabView react-navigation))

(def NavigationActionsBACK (aget NavigationActions "BACK"))
(def NavigationActionsINIT (aget NavigationActions "INIT"))
(def NavigationActionsNAVIGATE (aget NavigationActions "NAVIGATE"))
(def NavigationActionsRESET (aget NavigationActions "RESET"))
(def NavigationActionsSET_PARAMS (aget NavigationActions "SET_PARAMS"))
(def NavigationActionsURI (aget NavigationActions "URI"))
(def back (aget NavigationActions "back"))
(def init (aget NavigationActions "init"))
(def navigate (aget NavigationActions "navigate"))
(def reset  (aget NavigationActions "reset"))
(def setParams (aget NavigationActions "setParams"))
(def uri (aget NavigationActions "uri"))

(defn getStateForAction [router action]
  (.getStateForAction router action))

(defn getActionForPathAndParams [router path params]
  (.getActionForPathAndParams router path params))

(defn getPathAndParamsForState [router state]
  (.getPathAndParamsForState router state))

(defn getComponentForState [router state]
  (.getComponentForState router state))

(defn getComponentForRouteName [router route-name]
  (.getComponentForRouteName router route-name))

(defn getRouter [navigator]
  (.-router navigator))

(assert react-navigation)
(assert createNavigationContainer)
(assert StateUtils)
(assert PropTypes)
(assert addNavigationHelpers)
(assert NavigationActions)
(assert createNavigator)
(assert StackNavigator)
(assert TabNavigator)
(assert DrawerNavigator)
(assert StackRouter)
(assert TabRouter)
(assert Transitioner)
(assert CardStack)
(assert DrawerView)
(assert TabView)
(assert NavigationActionsBACK)
(assert NavigationActionsINIT)
(assert NavigationActionsNAVIGATE)
(assert NavigationActionsRESET)
(assert NavigationActionsSET_PARAMS)
(assert NavigationActionsURI)
(assert back)
(assert init)
(assert navigate)
(assert reset)
(assert setParams)
(assert uri)
